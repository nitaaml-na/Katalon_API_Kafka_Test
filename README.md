# Katalon Studio Automation Technical Test - API & Kafka Testing

Repositori ini berisi proyek otomatisasi pengujian (*automation testing*) menggunakan **Katalon Studio** untuk memenuhi kriteria uji teknis. Proyek ini mencakup pengujian **RESTful API** (skenario Producer & Consumer) dan integrasi **Kafka Message Consumer** di lingkungan lokal.

---

## 🚀 Cakupan Pengujian (Test Scope)

1. **RESTful API Automation (Producer & Consumer):**
   - **Producer Skenario:** Mengirim data pengguna baru menggunakan `POST` request ke endpoint ReqRes (`https://reqres.in`).
   - **Consumer Skenario:** Mengambil data pengguna menggunakan `GET` request, lalu melakukan validasi menyeluruh terhadap struktur *JSON response body* (`page`, `per_page`, data array, dan objek metadata).
2. **Kafka Message Testing (Consumer):**
   - Menggunakan komponen **Custom Keyword** di Katalon Studio dengan mengintegrasikan library Apache Kafka Client (`kafka-clients-3.4.0.jar`).
   - Melakukan koneksi ke Kafka Broker dan membaca (*consume*) pesan dari topik yang ditentukan.

---

## 📁 Struktur Komponen Proyek

* `Object Repository/` - Menyimpan konfigurasi endpoint dan parameter RESTful API (POST & GET).
* `Keywords/com.qa.kafka/` - Berisi berkas `KafkaConsumerKeyword.groovy` yang menangani logika koneksi dan konsumsi data dari cluster Apache Kafka.
* `Test Cases/` - Berisi skrip pengujian individual:
  * `TC_REST_API_Producer_Consumer`
  * `TC_Kafka_Consumer`
* `Test Suites/` - Berisi `Technical_Test_Suite` yang menggabungkan seluruh skenario pengujian di atas agar dapat dijalankan secara sekuensial.
* `Drivers/` - Tempat penyimpanan library `kafka-clients-3.4.0.jar`.
* `docker-compose.yml` - Konfigurasi containerisasi untuk menyalakan ekosistem Apache Kafka dan Zookeeper di komputer lokal.

---

## 🛠️ Prasyarat Sistem (Prerequisites)

Sebelum mengeksekusi proyek pengujian ini, pastikan sistem Anda telah terinstal perangkat lunak berikut:
* **Katalon Studio** (Versi 8.x atau yang lebih baru)
* **Docker Desktop** (Untuk menjalankan infrastruktur Kafka lokal)
* **Java JDK 8 / 11** (Sesuai dengan kebutuhan minimum environment Katalon)

---

## 🐳 Cara Menjalankan Lingkungan Kafka (Docker)

Aplikasi Kafka dijalankan di dalam Docker container agar tim penilai dapat menguji *script* ini secara instan tanpa perlu melakukan instalasi Kafka manual di sistem operasi.

1. Buka terminal atau Command Prompt (CMD) Anda.
2. Masuk ke direktori utama (*root folder*) proyek ini.
3. Jalankan perintah berikut untuk mengunduh image dan menyalakan container secara latar belakang (*detached mode*):
   ```bash
   docker compose up -d
   ```
4. Pastikan container telah berjalan sempurna dengan mengetikkan perintah `docker ps`. Layanan Kafka akan aktif di alamat `localhost:9092`.

---

## 💻 Langkah Mengeksekusi Pengujian (Execution Steps)

1. Buka aplikasi **Katalon Studio**.
2. Pilih **Open Project** dan arahkan ke folder hasil *clone* dari repositori ini.
3. Pada panel *Tests Explorer* sebelah kiri, buka folder **Test Suites**.
4. Klik dua kali pada file **`Technical_Test_Suite`**.
5. Klik ikon **Run** (tombol segitiga hijau) pada toolbar bagian atas Katalon Studio.
6. Hasil pengujian dan laporan verifikasi status data (*Assertion*) dapat dipantau langsung melalui tab **Log Viewer** atau folder **Reports**.

---

## 📝 Catatan Tambahan (Notes)
* Pengujian API diarahkan ke mock server publik **ReqRes.in**.
* Konfigurasi Kafka Consumer di dalam *script* secara bawaan diarahkan ke `localhost:9092` dengan topik `test-topic`. Parameter ini dapat diubah secara dinamis pada file Test Case jika anda ingin mengarahkannya ke *remote cloud cluster broker* tertentu.
