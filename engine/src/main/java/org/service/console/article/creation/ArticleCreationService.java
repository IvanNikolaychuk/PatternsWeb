package org.service.console.article.creation;

import org.model.article.Article;
import org.model.article.Tag;
import org.model.article.code.ArticleCode;
import org.model.article.code.ClassSection;
import org.model.article.code.SingleClass;
import org.service.ArticleService;
import org.service.decoration.CodeToHtmlConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ArticleCreationService {
    private static final String END = "end";

    private CodeToHtmlConverter codeToHtmlConverter;
    private ArticleService articleService;

    public ArticleCreationService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/engine-module-context.xml");
        codeToHtmlConverter = context.getBean(CodeToHtmlConverter.class);
        articleService = context.getBean(ArticleService.class);
    }

    public static void main(String[] args) throws Exception {
        final ArticleCreationService articleCreationService = new ArticleCreationService();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Article name: ");
        final String articleName = scanner.nextLine();

        System.out.println("Article preview: ");
        final String preview = scanner.nextLine();

        System.out.println("Article tags: ");
        final Set<Tag> tags = articleCreationService.readTags(scanner);

        System.out.println("Path to base folder with single classes and class sections: ");
        final String pathToBaseFolder = scanner.nextLine();
        File baseFolder = new File(pathToBaseFolder);


        Set<SingleClass> singleClasses = articleCreationService.extractSingleClasses(baseFolder);
        Set<ClassSection> sections = articleCreationService.extractClassSections(baseFolder);

        Article article = new Article(articleName, preview, new ArticleCode(singleClasses, sections), tags);

        articleCreationService.create(article);

    }

    private void create(Article article) {
        articleService.save(article);
    }

    private Set<Tag> readTags(Scanner scanner) {
        String nextTag = scanner.nextLine();

        Set<Tag> tags = new HashSet<>();
        while (!nextTag.equals(END)) {
            tags.add(new Tag(nextTag));
            nextTag = scanner.nextLine();
        }

        return tags;
    }

    private Set<ClassSection> extractClassSections(File baseFolder) throws Exception {
        File folderWithClassSections = new File(baseFolder.getAbsolutePath() + "\\sections");

        Set<ClassSection> classSections = new HashSet<>();

        for (File folder : folderWithClassSections.listFiles()) {
            if (folder.isFile()) {
                throw new IllegalArgumentException("On \\sections level should be only folders");
            }

            Set<SingleClass> singleClasses = new HashSet<>();

            for (File file : folder.listFiles()) {
                singleClasses.add(toSingleClass(file));
            }

            classSections.add(new ClassSection(singleClasses));
        }

        return classSections;
    }

    private Set<SingleClass> extractSingleClasses(File baseFolder) throws Exception {
        File folderWithSingleClasses = new File(baseFolder.getAbsolutePath() + "\\single");

        Set<SingleClass> classes = new HashSet<>();
        for (File singleClassFile : folderWithSingleClasses.listFiles()) {
            classes.add(toSingleClass(singleClassFile));
        }

        return classes;
    }

    private SingleClass toSingleClass(File singleClassFile) throws Exception {
        try (FileInputStream inputStream = new FileInputStream(singleClassFile)) {
            byte[] content = new byte[(int) singleClassFile.length()];
            inputStream.read(content);
            return codeToHtmlConverter.convert(singleClassFile.getName(), new String(content));
        }
    }
}
