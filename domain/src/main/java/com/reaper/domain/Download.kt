package com.reaper.domain

import java.io.File

sealed class Download {
    data class Progress(val percent: Int) : Download()
    data class Finished(val file: File) : Download()
    data class Error(val message: String) : Download()
}
