const fs = require('fs').promises;
const path = require('path');
const marked = require('marked');
const markedHighlight = require('marked-highlight');
const cheerio = require('cheerio');
const hljs = require('highlight.js');

const markedInstance = new marked.Marked(
    markedHighlight.markedHighlight({
        langPrefix: 'hljs language-',
        highlight(code, lang, info) {
            const language = hljs.getLanguage(lang) ? lang : 'plaintext';
            return hljs.highlight(code, { language }).value;
        }
    })
);

init();

async function init() {
    const distDirPath = path.join(__dirname, 'dist');
    const srcDirPath = path.join(__dirname, 'src');
    const isDistFolderExist = await fs.stat(distDirPath)
        .then(stat => stat.isDirectory())
        .catch(() => false);
    if (isDistFolderExist) {
        await fs.rmdir(distDirPath, { recursive: true });
    }
    await fs.mkdir(distDirPath);
    const configsFileContent = await fs.readFile(path.join(srcDirPath, 'config.json'), 'utf8');
    const templateFileContent = await fs.readFile(path.join(srcDirPath, 'template.html'), 'utf8');
    const highlightJsStyle = await fs.readFile('./node_modules/highlight.js/styles/atom-one-dark.min.css', "utf8");
    const $ = cheerio.load(templateFileContent);
    const configs = JSON.parse(configsFileContent);
    for (const config of configs) {
        const markdownFileContent = await fs.readFile(path.join(__dirname, config.markdown), 'utf8');
        const markdownAsHtmlContent = markedInstance.parse(markdownFileContent);
        const markdownTokens = markedInstance.lexer(markdownFileContent);
        const heading = markdownTokens.filter(token => token.type === 'heading' && token.depth === 1).map(token => token.text)[0];
        const htmlFileName = heading.toLowerCase().replace(/\s+/g, '-');
        $('title').text(heading);
        $('style').text(highlightJsStyle);
        $('main').append(markdownAsHtmlContent);
        fs.writeFile(path.join(distDirPath, `${htmlFileName}.html`), $.html());
    }
}