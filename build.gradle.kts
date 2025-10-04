plugins {
    id("net.labymod.labygradle")
    id("net.labymod.labygradle.addon")
}

val versions = providers.gradleProperty("net.labymod.minecraft-versions").get().split(";")
group = "dev.jumpingpxl.addons"
version = providers.environmentVariable("VERSION").getOrElse("1.0.0")

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

labyMod {
    defaultPackageName = "dev.jumpingpxl.addons.autoreconnect" //change this to your main package name (used by all modules)
    
    addonInfo {
        namespace = "autoreconnect"
        displayName = "AutoReconnect"
        author = "JumpingPxl"
        description = "Automatically reconnects to the server you got disconnected from"
        version = System.getenv().getOrDefault("VERSION", "0.0.1")
    }

    minecraft {
        registerVersion(versions.toTypedArray()) {
            runs {
                getByName("client") {
                    // When the property is set to true, you can log in with a Minecraft account
                    // devLogin = true
                }
            }
        }
    }
}

subprojects {
    plugins.apply("net.labymod.labygradle")
    plugins.apply("net.labymod.labygradle.addon")

    group = rootProject.group
    version = rootProject.version
}
