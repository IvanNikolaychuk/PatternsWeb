package org.service.console.article.creation;

import org.model.article.Article;
import org.model.article.Tag;
import org.model.article.code.ArticleCode;
import org.model.article.code.ClassSection;
import org.model.article.code.SingleClass;
import org.service.ArticleService;
import org.service.decoration.CodeToHtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * It's a separate service and not placed as part of {@link ArticleService} because it's intent is different.
 * This one is designed for product owner usage and is not expected to be used by clients.
 */
@Service
public class ArticleCreationService {

    private final CodeToHtmlConverter codeToHtmlConverter;
    private final ArticleService articleService;

    @Autowired
    public ArticleCreationService(CodeToHtmlConverter codeToHtmlConverter, ArticleService articleService) {
        this.codeToHtmlConverter = codeToHtmlConverter;
        this.articleService = articleService;
    }

    public void create(String articleName, String preview, Set<String> tagNames,
                          String pathToDirWithClasses) throws Exception {

        ArticleCode code = new ArticleCode(
                extractSingleClasses(pathToDirWithClasses), extractClassSections(pathToDirWithClasses));

        articleService.save(new Article(articleName, preview, code, toTags(tagNames)));
    }

    private Set<ClassSection> extractClassSections(String pathToFolderWithClasses) throws Exception {
        File folderWithClassSections = new File(pathToFolderWithClasses + "\\sections");

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

    private Set<SingleClass> extractSingleClasses(String pathToFolderWithClasses) throws Exception {
        File folderWithSingleClasses = new File(pathToFolderWithClasses + "\\single");

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

            final String className = singleClassFile.getName().substring(0, singleClassFile.getName().indexOf("."));
            return codeToHtmlConverter.convert(className, new String(content));
        }
    }

    private Set<Tag> toTags(Set<String> tagNames) {
        Set<Tag> tags = new HashSet<>(tagNames.size());

        tags.addAll(tagNames.stream().map(Tag::new).collect(Collectors.toList()));

        return tags;
    }
}
