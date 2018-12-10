import com.hotsx.app.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream

fun saveAudio(body: ResponseBody, savePath: String) {
    GlobalScope.launch(Dispatchers.Default) {
        log("savePath-$savePath")
        val audioFile = File(savePath)
        audioFile.exists()
        val fileSize = body.contentLength()
        log("fileSize:$fileSize")
        var downSize: Long = 0
        val inputStream = body.byteStream()
        val outputStream = FileOutputStream(audioFile)
        val byteArray = ByteArray(4096)
        while (true) {
            val read = inputStream.read(byteArray)
            if (read == -1) break
            outputStream.write(byteArray, 0, read)
            downSize += read
        }
        outputStream.flush()
        inputStream.close()
        outputStream.close()
    }
}
