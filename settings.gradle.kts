pluginManagement {
    repositories {
        //google()
        //mavenCentral()
        //gradlePluginPortal()
        maven(url="https://maven.aliyun.com/repository/google")
        maven(url="https://maven.aliyun.com/repository/public")  //jcenter
        maven(url="https://maven.aliyun.com/repository/central")
        maven(url="https://maven.aliyun.com/repository/gradle-plugin")
    }
}
rootProject.name = "MathIDE"

include("app")

/*include ':app', ':domain', ':data', ':editorkit'

include ':features:feature-editor',
        ':features:feature-explorer',
        ':features:feature-fonts',
        ':features:feature-settings',
        ':features:feature-themes',
        ':features:feature-ui',
        ':features:feature-utils'

include ':filesystems:filesystem-base',
        ':filesystems:filesystem-local'
// TODO ':filesystems:filesystem-dropbox'
// TODO ':filesystems:filesystem-googledrive'
// TODO ':filesystems:filesystem-ftp'

include ':languages:language-base',
        ':languages:language-plaintext',
        ':languages:language-javascript',
        ':languages:language-json',
        ':languages:language-actionscript',
        ':languages:language-csharp',
        ':languages:language-groovy',
        ':languages:language-html',
        ':languages:language-c',
        ':languages:language-cpp',
        ':languages:language-java',
        ':languages:language-kotlin',
        ':languages:language-lua',
        ':languages:language-lisp',
        ':languages:language-markdown',
        ':languages:language-php',
        ':languages:language-python',
        ':languages:language-shell',
        ':languages:language-sql',
        ':languages:language-typescript',
        ':languages:language-visualbasic',
        ':languages:language-xml',
        ':languages:language-julia'
        */