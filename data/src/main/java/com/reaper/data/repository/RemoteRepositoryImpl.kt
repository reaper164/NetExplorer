package com.reaper.data.repository

import android.util.Log
import com.reaper.data.remote.api.ApiService
import com.reaper.domain.Download
import com.reaper.domain.model.ChrootParams
import com.reaper.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

class RemoteRepositoryImpl(
    private val apiService: ApiService
) : RemoteRepository {

    private val TAG = this.javaClass.simpleName

    override fun downloadChroot(params: ChrootParams): Flow<Download> = flow {
        emit(Download.Progress(percent = 0))
        var deleteFile = true
        val archive = File(params.savePath, "chroot.tar.gz")
        if(archive.exists())
            archive.delete()
        Log.e(TAG, "Safe archive: ${archive.absolutePath}")


        try {
            val responseBody = apiService.downloadFile(params.url).body()

            responseBody?.let { body ->
                body.byteStream().use { inputStream ->
                    archive.outputStream().use { outputStream ->
                        val totalBytes = body.contentLength()
                        val data = ByteArray(8_192)
                        var progressBytes = 0L

                        while (true) {
                            val bytes = inputStream.read(data)

                            if (bytes == -1) {
                                break
                            }

                            outputStream.write(data, 0, bytes)
                            progressBytes += bytes

                            emit(Download.Progress(percent = ((progressBytes * 100) / totalBytes).toInt()))
                        }

                        when {
                            progressBytes < totalBytes ->
                                throw Exception("missing bytes")
                            progressBytes > totalBytes ->
                                throw Exception("too many bytes")
                            else ->
                                deleteFile = false
                        }
                    }
                }
            }
            emit(Download.Finished(archive))
        } finally {
            // check if download was successful
            if (deleteFile) {
                Log.e(TAG, "Delete archive: ${archive.absolutePath}")
                archive.delete()
            }
        }

    }
        .flowOn(Dispatchers.IO)
        .distinctUntilChanged()
}