package com.dtxpads.vtuber.skin

/**
 * Batas ukuran skin. Angka ini adalah batas awal PROYEK VTuber yang disetujui pengguna
 * (8 MB per file, 24 MB per paket). Bukan klaim bahwa DTX Kendang memakai batas yang sama
 * (DTX: 4 MB). Wajib diterapkan di impor (CP2), validasi paket (CP3), dan unduhan (CP7).
 */
object SkinLimits {
    const val MAX_FILE_BYTES: Long = 8L * 1024 * 1024
    const val MAX_PACKAGE_BYTES: Long = 24L * 1024 * 1024

    /** Pesan error bila ukuran file tidak sah, null bila lolos. */
    fun fileSizeError(name: String, sizeBytes: Long): String? = when {
        sizeBytes <= 0L -> "File $name kosong"
        sizeBytes > MAX_FILE_BYTES ->
            "File $name terlalu besar (${sizeBytes / 1024} KB; maksimum ${MAX_FILE_BYTES / 1024} KB)"
        else -> null
    }

    /** Pesan error bila total ukuran paket melebihi batas, null bila lolos. */
    fun packageSizeError(totalBytes: Long): String? =
        if (totalBytes > MAX_PACKAGE_BYTES) {
            "Paket skin terlalu besar (${totalBytes / 1024} KB; maksimum ${MAX_PACKAGE_BYTES / 1024} KB)"
        } else null
}
