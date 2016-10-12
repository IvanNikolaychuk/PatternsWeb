package org.service.decoration.helpers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class StateManager {
    private StateManager.State state;

    public StateManager() {
        state = new BeforeClassState();
    }

    public ClassContextConverter.State getActualState(String newWord) {
        state = state.nextState(newWord);
        return state.getState();
    }

    // TODO: This is not normal. I want StateManager instance to be created on each call and to reset it's state in
    // TODO: constructor, not manually.
    public void resetState() {
        state = new BeforeClassState();
    }

    public interface State {
        // returns new state if state needs to be changed, current state if change shouldn't change
        State nextState(String newWord);
        ClassContextConverter.State getState();
    }

    private class BeforeClassState implements State {

        @Override
        public State nextState(String newWord) {
            if (newWord.contains("{")) {
                return new InClassState();
            }

            return commentStateOrCurrentState(newWord, this);
        }

        @Override
        public ClassContextConverter.State getState() {
            return ClassContextConverter.State.BEFORE_CLASS;
        }
    }

    private class InClassState implements State {

        @Override
        public State nextState(String newWord) {
            if (newWord.contains("{")) {
                return new InMethodState();
            }

            return commentStateOrCurrentState(newWord, this);
        }

        @Override
        public ClassContextConverter.State getState() {
            return ClassContextConverter.State.IN_CLASS;
        }
    }

    private class InMethodState implements State {

        @Override
        public State nextState(String newWord) {
            if (newWord.contains("}")) {
                return new InClassState();
            }

            return commentStateOrCurrentState(newWord, this);
        }

        @Override
        public ClassContextConverter.State getState() {
            return ClassContextConverter.State.IN_METHOD;
        }
    }


    private class InCommentState implements State {
        private final String firstCommentSign;
        private String prevWord;
        private final State prevState;

        InCommentState(String firstCommentSign, State beforeCommentState) {
            this.firstCommentSign = firstCommentSign;
            this.prevWord = firstCommentSign;
            this.prevState = beforeCommentState;
        }

        @Override
        public State nextState(String newWord) {
            if (oneLineComment()) {
                return isNewLineSign(newWord) ? prevState : this;
            }

            boolean isStateValid = !prevWord.contains("*/");
            this.prevWord = newWord;

            return isStateValid ? this : prevState;
        }

        @Override
        public ClassContextConverter.State getState() {
            return ClassContextConverter.State.IN_COMMENT;
        }

        private boolean isNewLineSign(String newWord) {
            return newWord.contains("\n");
        }

        private boolean oneLineComment() {
            return firstCommentSign.matches("//+");
        }
    }


    private State commentStateOrCurrentState(String newWord, State beforeState) {
        if (newWord.contains("//")) {
            return new InCommentState("//", beforeState);
        } else if (newWord.contains("/*")) {
            return new InCommentState("/*", beforeState);
        }

        return beforeState;
    }
}
