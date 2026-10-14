# DEVICE_NOTES.md — Perangkat Uji Awal: Infinix Smart 10 Plus

**Status verifikasi: BELUM diverifikasi di perangkat nyata.** Tidak ada pengujian pada perangkat yang sudah dilakukan.

## Data pihak ketiga (dicari via web 2026-10-14; bukan hasil baca dari perangkat)
Beberapa situs spesifikasi (GSMArena, 91mobiles, Smartprix, dll.) sepakat pada: rilis Juni 2025; layar IPS 6,67" 720×1600, 120 Hz; Unisoc T7250 octa-core (2×Cortex-A75 1,8 GHz + 6×Cortex-A55 1,6 GHz, 12 nm);
GPU Mali-G57 MP1; RAM 4 GB / 8 GB (listing berbeda), 128 GB eMMC; kamera depan 8 MP; baterai 6000 mAh; **Android 15 + XOS 15.1** (satu toko menulis "Android 15 / 15 Go edition").
Satu sumber (Kimovil) menampilkan "Android 14" — bertentangan; versi nyata harus dibaca dari perangkat.
Implikasi: kelas entry-level (lihat ARCHITECTURE §6c).

## Cara verifikasi (CP1)
Buka aplikasi → PENGATURAN → "INFO PERANGKAT" → SALIN INFO → tempel ke percakapan. Yang dibaca langsung dari perangkat: merek, model, versi Android + API, patch keamanan, ABI, jumlah core,
RAM total, resolusi/dpi, **ukuran page memori** (relevan untuk 16 KB), kamera depan, status izin overlay. Hasil ini yang boleh dipakai sebagai dasar klaim; sampai ada, semua di atas = "klaim situs".

## Yang HARUS diuji nyata sebelum klaim (dikaitkan ke TEST_PLAN)
Kamera depan di latar belakang & foreground service (T-6.x), overlay tertangkap perekam (T-6.4), beban CPU/panas tracking (T-5.3), perilaku XOS terhadap service latar (T-6.5).
