/*
 * Copyright 2013 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gaiden

/**
 * A document builder builds from a document source to a document.
 *
 * @author Hideki IGARASHI
 * @author Kazuki YAMAMOTO
 */
class DocumentBuilder {

    /**
     * Builds a document from a document source.
     *
     * @param documentSource the document source to be built
     * @return {@link Document}'s instance
     */
    Document build(DocumentSource documentSource) {
        def templateEngine = createTemplateEngine()
        def builder = new MarkdownPageBuilder(templateEngine: templateEngine)
        def pages = buildPages(documentSource, builder)

        new Document(pages: pages)
    }

    private List<Page> buildPages(DocumentSource documentSource, builder) {
        documentSource.pageSources.collect { pageSource ->
            def binding = createBindingMap()
            builder.build(pageSource, binding)
        }
    }

    private TemplateEngine createTemplateEngine() {
        def templateFile = new File(GaidenConfig.instance.templatePath)
        new TemplateEngine(template: templateFile.text)
    }

    private Map createBindingMap() {
        def config = GaidenConfig.instance
        [
            title: config.title,
        ]
    }

}
