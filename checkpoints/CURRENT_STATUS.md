# CURRENT_STATUS.md

Terakhir diperbarui: 2026-10-14 (CP0 PASSED; CP1 IN_PROGRESS)

- **Tahap aktif**: 1 — Fondasi aplikasi dan UI
- **Checkpoint terakhir lulus**: CP0 (PASSED, bukti di CP00.md)
- **Selesai dan diuji**: CP0 (dokumen; pemeriksaan konsistensi dijalankan)
- **Selesai tetapi belum diverifikasi**: seluruh kode CP1 (hanya pemeriksaan statis; belum dikompilasi, belum ada unit test dijalankan, belum dijalankan di perangkat)
- **Belum selesai**: CP2–CP8
- **Bug diketahui**: tidak ada yang teramati; error kompilasi mungkin ada karena belum pernah dibuild
- **File penting berubah**: seluruh proyek Android baru (lihat CP01.md §5); dokumen CP0 diperbarui
- **Perintah & hasil build terakhir**: tidak ada — lingkungan AI tanpa Android SDK/Gradle/kotlinc/jaringan. Perintah CI: `gradle testDebugUnitTest assembleDebug --stacktrace`
- **Tes terakhir**: pemeriksaan dokumen CP0 = PASS; pemeriksaan statis kode CP1 = PASS (bukan kompilasi); unit test JVM = NOT_RUN
- **APK**: tidak ada (`release/` kosong dengan sengaja)

## Posisi proyek
Audit DTX selesai (CP0 PASSED). CP1: kode fondasi + UI portrait ala DTX tertulis; menunggu build nyata lewat GitHub Actions.

## Keterbatasan terbuka
1. Belum ada screenshot UI DTX → kemiripan visual tak bisa dinilai.
2. Infinix Smart 10 Plus belum diverifikasi (data situs saja; satu sumber menyebut Android 14, mayoritas Android 15/XOS 15.1).
3. Versi MediaPipe/CameraX belum diperiksa (CP5); risiko 16 KB (R3).
4. URL repo skin belum ditentukan → CP7 menunggu.

## Yang dibutuhkan dari pengguna untuk menutup CP1
1. Push ke GitHub (repo baru, mis. privat) dan jalankan workflow **Build Debug APK**.
2. Kirim hasil: hijau/merah + log error kompilasi (bila merah) + ringkasan `testDebugUnitTest`.
3. Install APK di Infinix, buka layar utama & Pengaturan, tekan **SALIN INFO**, tempel hasilnya.
4. (Opsional) screenshot UI DTX asli.

## Langkah berikutnya (spesifik)
Setelah CP1 PASSED: CP2 — picker PNG (SAF), validasi (PNG, alpha, dimensi, `SkinLimits` 8 MB), decode aman (downsample), tampil di preview, simpan pilihan, pesan error jelas; hubungkan tombol "IMPOR / GANTI SKIN"; bangun `AvatarModel.singlePng` dari file terimpor.
