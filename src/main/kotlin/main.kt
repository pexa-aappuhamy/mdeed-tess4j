import net.sourceforge.tess4j.ITesseract
import net.sourceforge.tess4j.Tesseract
import org.apache.pdfbox.Loader
import org.apache.pdfbox.rendering.PDFRenderer
import java.io.File


fun main() {
    val tessInstance = Tesseract()
    tessInstance.setVariable("debug_file", "/dev/null"); // suppress tesseract warnings
    val sourceFile = File("assets/scanned.pdf")
    val scannedDocument = Loader.loadPDF(sourceFile)

    val renderDocument = PDFRenderer(scannedDocument)
    val buffImage = renderDocument.renderImage(0)

    val format = mutableListOf(ITesseract.RenderedFormat.PDF)
    val outputFile = "searchable"

    try {
        tessInstance.createDocumentsWithResults(buffImage, outputFile, outputFile, format, 0)
        print("Converted to Searchable PDF.")
    }
    catch (e: Exception) {
        throw Exception("Error converting PDF.")
    }

}