# CHANGELOG.md

## 2026-10-14 — CP0 PASSED + CP1 (kode, belum diverifikasi)
- CP0: audit lanjutan (PROMPT_SKIN, SkinValidator, SkinRepository, UI DTX); ditemukan dokumentasi DTX tidak sinkron dengan kode (applicationId); skrip konsistensi dokumen dijalankan → PASS; status CP0 → PASSED.
- Keputusan pengguna dicatat: package ID `com.dtxpads.vtuber`; batas 8 MB/file & 24 MB/paket; MVP PNG tunggal; repo skin fleksibel (CP7 menunggu); perangkat uji Infinix Smart 10 Plus.
- Tambah `docs/DEVICE_NOTES.md`; `ARCHITECTURE.md` §6a–6c; `TEST_PLAN.md` T-1.4, T-1.5, T-2.4, T-3.3, T-7.4.
- CP1: proyek Gradle, CI, tema ala DTX, HomeScreen, SettingsScreen (aksen, izin overlay, info perangkat), `AvatarModel`, `SkinLimits`, unit test. Dampak pada fitur berjalan: tidak ada (belum ada fitur sebelumnya).
- Alasan versi toolchain = DTX: terbukti dibuild di CI pengguna.

## 2026-10-14 — Checkpoint 0 (awal)
- Tambah dokumen CP0 awal (analisis, master, plan, arsitektur, tes, status, tasks, README).
