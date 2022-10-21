import net.sourceforge.tess4j.ITesseract
import net.sourceforge.tess4j.Tesseract
import java.lang.Exception



fun main() {
    val tessInstance: ITesseract = Tesseract()
    // TO DO: Convert pdf to image buffer
//    val image = ImageIO.read(File("assets/scannedpdf.png"))

    val format = mutableListOf(ITesseract.RenderedFormat.PDF)
    val fileName = "searchablepdf"

    try {
        tessInstance.createDocumentsWithResults("assets/scannedpdf.png", fileName, format, 0)
        print("Converted to Searchable PDF.")
    }
    catch (e: Exception) {
        throw Exception("Error converting PDF.")
    }

}
