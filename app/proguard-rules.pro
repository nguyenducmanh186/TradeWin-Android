# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn android.arch.util.paging.CountedDataSource
-dontwarn android.arch.persistence.room.paging.LimitOffsetDataSource
-dontwarn net.fortuna.ical4j.model.**
-keep class io.plaidapp.data.api.dribbble.model.** { *; }
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-keepnames class org.jsoup.nodes.Entities
-keepattributes *Annotation*, Signature, Exception
-dontwarn kotlinx.coroutines.flow.**

-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep class com.bumptech.glide.GeneratedAppGlideModuleImpl
-keep class * implements java.io.Serializable { *; }