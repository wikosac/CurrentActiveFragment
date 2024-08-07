# CurrentActiveFragment
[![](https://jitpack.io/v/wikosac/CurrentActiveFragment.svg)](https://jitpack.io/#wikosac/CurrentActiveFragment)

Library to get current active fragment easily

# Gradle
build.gradle.kts
```kotlin
implementation("com.github.wikosac:CurrentActiveFragment:1.0.2")
```
settings.gradle.kts
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

# Example
```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var currentActiveFragment: CurrentActiveFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentActiveFragment = CurrentActiveFragment(this)
        currentActiveFragment.get(R.id.nav_host_fragment_activity_main) { activeFragment ->
            val fragmentName = activeFragment.javaClass.simpleName
            Log.d("CurrentActiveFragment", "Current active fragment: $fragmentName")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::currentActiveFragment.isInitialized) {
            currentActiveFragment.unregister()
        }
    }
}
```

Support me by starring this repository ^_^
