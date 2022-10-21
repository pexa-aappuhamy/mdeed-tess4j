import net.sourceforge.tess4j.ITesseract
import net.sourceforge.tess4j.Tesseract

import java.io.File

fun main() {
    val instance: ITesseract = Tesseract()
    val scannedDeed = File("assets/scannedpdf.png")
    val searchDeed = instance.doOCR(scannedDeed)
    print(searchDeed)
}
