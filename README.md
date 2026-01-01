

# 📝 ToDoKMP — Kotlin Multiplatform To-Do Uygulaması

**ToDoKMP**, Kotlin Multiplatform kullanılarak geliştirilmiş,
**Android · Desktop (JVM) · iOS** üzerinde çalışabilen modern bir yapılacaklar listesi uygulamasıdır.

Uygulama tamamen **Compose Multiplatform**, **Realm Kotlin SDK**, **Voyager Navigation** ve **Koin DI** ile inşa edilmiştir.

---

## 🚀 Özellikler

* ✍️ Görev ekleme / güncelleme / silme
* ⭐ Favori görev işaretleme
* ✅ Görev tamamlama
* 🔄 Realm Flow ile gerçek zamanlı veri güncellenmesi
* 🧭 Voyager ile akıcı sayfa geçişleri
* 🗄️ Realm veritabanı ile local persistence
* 💉 Koin ile Dependency Injection
* 🎨 Tamamen Compose Multiplatform UI
* 🧩 Clean MVVM mimarisi

---

## 🧱 Kullanılan Teknolojiler

| Katman      | Teknoloji                     |
| ----------- | ----------------------------- |
| Arayüz (UI) | Compose Multiplatform         |
| Navigasyon  | Voyager                       |
| Veritabanı  | Realm Kotlin SDK              |
| DI          | Koin                          |
| Mimari      | MVVM + ScreenModel            |
| Platformlar | Android · Desktop (JVM) · iOS |
| Dil         | Kotlin                        |
| Build       | Gradle KTS                    |

---

## 📂 Proje Yapısı

```
composeApp/
 ├── data/
 │   └── MongoDB.kt
 ├── domain/
 │   ├── RequestState.kt
 │   ├── TaskAction.kt
 │   └── ToDoTask.kt
 ├── presentation/
 │   ├── components/
 │   ├── screen/
 │   │   ├── home/
 │   │   └── task/
 └── App.kt
```

---

## 🖥️ Desteklenen Platformlar

| Platform      | Durum                                      |
| ------------- | ------------------------------------------ |
| Android       | ✅ Destekleniyor                            |
| Desktop (JVM) | ✅ Destekleniyor                            |
| iOS           | ⚠️ Sadece macOS ortamında build edilebilir |

---

## 🛠️ Kurulum ve Çalıştırma

### 1️⃣ Projeyi Klonla

```bash
git clone https://github.com/atakanunlu/ToDoKMP.git
cd ToDoKMP
```

### 2️⃣ Temiz Build

```bash
./gradlew clean
```

### 3️⃣ Desktop Uygulamayı Çalıştır

```bash
./gradlew run
```

### 4️⃣ Android

Android Studio ile projeyi aç → Emulator veya fiziksel cihaz seç → **Run**

---

## 📦 Realm Veritabanı

Realm yapılandırması:

```kotlin
val config = RealmConfiguration.Builder(
    schema = setOf(ToDoTask::class)
).compactOnLaunch().build()
```

Veriler **Flow** ile reaktif şekilde dinlenir.

---

## 🔁 RequestState Yapısı

Tüm UI durumları merkezi olarak yönetilir:

```kotlin
sealed class RequestState<out T> {
    object Idle
    object Loading
    data class Success<T>(val data: T)
    data class Error(val message: String)
}
```

---

## 🧪 Test

```bash
./gradlew jvmTest
```

---
