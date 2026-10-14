package com.dtxpads.vtuber.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dtxpads.vtuber.BuildConfig
import com.dtxpads.vtuber.device.OverlayPermission
import com.dtxpads.vtuber.ui.theme.AppTextSize
import com.dtxpads.vtuber.ui.theme.CtrlPanelColor
import com.dtxpads.vtuber.ui.theme.GroupItemSpacing
import com.dtxpads.vtuber.ui.theme.GroupOuterPaddingH
import com.dtxpads.vtuber.ui.theme.GroupOuterPaddingV
import com.dtxpads.vtuber.ui.theme.PadLineColor
import com.dtxpads.vtuber.ui.theme.TextDim
import com.dtxpads.vtuber.ui.theme.TextMain
import com.dtxpads.vtuber.ui.theme.ThemeState
import com.dtxpads.vtuber.ui.theme.WarnAmber

enum class StatusLevel { OK, WARN, OFF }

data class StatusItem(val label: String, val value: String, val level: StatusLevel)

/**
 * Layar utama CP1. Prioritas (Master Prompt §3): preview, impor skin, kamera+tracking, floating,
 * status, pengaturan. Hanya PENGATURAN yang berfungsi di CP1; tombol lain nonaktif dan
 * berlabel checkpoint tujuannya (tidak pura-pura berfungsi).
 */
@Composable
fun HomeScreen(resumeTick: Int, onOpenSettings: () -> Unit) {
    val context = LocalContext.current
    val overlayGranted = remember(resumeTick) { OverlayPermission.isGranted(context) }
    val statuses = listOf(
        StatusItem("Avatar", "Belum ada (CP2)", StatusLevel.OFF),
        StatusItem("Kamera", "Mati (CP5)", StatusLevel.OFF),
        StatusItem("Tracking", "Mati (CP5)", StatusLevel.OFF),
        StatusItem("Floating", "Mati (CP6)", StatusLevel.OFF),
        StatusItem(
            "Izin overlay",
            if (overlayGranted) "Diizinkan" else "Belum diizinkan",
            if (overlayGranted) StatusLevel.OK else StatusLevel.WARN
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = GroupOuterPaddingH, vertical = GroupOuterPaddingV),
        verticalArrangement = Arrangement.spacedBy(GroupItemSpacing)
    ) {
        HeaderBar()
        AvatarPreview(Modifier.weight(1f).fillMaxWidth())
        StatusPanel(statuses)
        ActionPanel(onOpenSettings)
    }
}

@Composable
private fun HeaderBar() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("VTUBER DTX", color = TextMain, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.weight(1f))
        Text("CP1 · v${BuildConfig.VERSION_NAME}", color = TextDim, fontSize = 10.sp)
    }
}

/** Area preview: kotak-kotak (indikator transparansi untuk PNG alpha) + pesan kosong. */
@Composable
private fun AvatarPreview(modifier: Modifier) {
    val shape = RoundedCornerShape(5.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .background(CtrlPanelColor)
            .border(1.dp, PadLineColor, shape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val s = 16.dp.toPx()
            val cols = (size.width / s).toInt() + 1
            val rows = (size.height / s).toInt() + 1
            for (r in 0 until rows) {
                for (c in 0 until cols) {
                    if ((r + c) % 2 == 0) {
                        drawRect(
                            color = Color(0xFF232325),
                            topLeft = Offset(c * s, r * s),
                            size = Size(s, s)
                        )
                    }
                }
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Belum ada avatar", color = TextMain, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text("Impor PNG transparan tersedia di CP2", color = TextDim, fontSize = 11.sp)
        }
    }
}

@Composable
private fun StatusPanel(items: List<StatusItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(CtrlPanelColor)
            .padding(horizontal = GroupOuterPaddingH, vertical = GroupOuterPaddingV),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items.forEach { item ->
            val dot = when (item.level) {
                StatusLevel.OK -> ThemeState.accentColor
                StatusLevel.WARN -> WarnAmber
                StatusLevel.OFF -> TextDim
            }
            val valueColor = when (item.level) {
                StatusLevel.OK -> TextMain
                StatusLevel.WARN -> WarnAmber
                StatusLevel.OFF -> TextDim
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(8.dp).clip(CircleShape).background(dot))
                Spacer(Modifier.size(8.dp))
                Text(item.label, color = TextDim, fontSize = AppTextSize, modifier = Modifier.weight(1f))
                Text(item.value, color = valueColor, fontSize = AppTextSize, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun ActionPanel(onOpenSettings: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(CtrlPanelColor)
            .padding(horizontal = GroupOuterPaddingH, vertical = GroupOuterPaddingV),
        verticalArrangement = Arrangement.spacedBy(GroupItemSpacing)
    ) {
        ActionButton(
            label = "IMPOR / GANTI SKIN",
            icon = Icons.Filled.Image,
            onClick = {},
            enabled = false,
            caption = "Tersedia di CP2",
            modifier = Modifier.fillMaxWidth()
        )
        Row(horizontalArrangement = Arrangement.spacedBy(GroupItemSpacing)) {
            ActionButton(
                label = "KAMERA",
                icon = Icons.Filled.Videocam,
                onClick = {},
                enabled = false,
                caption = "CP5",
                modifier = Modifier.weight(1f)
            )
            ActionButton(
                label = "TRACKING",
                icon = Icons.Filled.Face,
                onClick = {},
                enabled = false,
                caption = "CP5",
                modifier = Modifier.weight(1f)
            )
        }
        ActionButton(
            label = "FLOATING",
            icon = Icons.Filled.Layers,
            onClick = {},
            enabled = false,
            caption = "Tersedia di CP6",
            modifier = Modifier.fillMaxWidth()
        )
        ActionButton(
            label = "PENGATURAN",
            icon = Icons.Filled.Settings,
            onClick = onOpenSettings,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
