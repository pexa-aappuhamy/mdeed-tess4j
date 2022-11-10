import net.sourceforge.tess4j.ITessAPI
import net.sourceforge.tess4j.Tesseract
import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.rendering.PDFRenderer
import java.io.File



fun main() {
    val tessInstance = Tesseract()
    tessInstance.setVariable("debug_file", "/dev/null") // suppress tesseract warnings
    val sourceFile = File("assets/scanned.pdf")
    val scannedDocument = Loader.loadPDF(sourceFile)

    val renderDocument = PDFRenderer(scannedDocument)
    val buffImage = renderDocument.renderImage(0)

    val height = buffImage.height

    val searchableDocument = PDDocument()
    val page = PDPage(PDRectangle.A4)
    val font = PDType1Font.HELVETICA

    searchableDocument.addPage(page)

    val textStream = PDPageContentStream(searchableDocument,page)


    val ocrResults = tessInstance.getWords(buffImage, ITessAPI.TessPageIteratorLevel.RIL_TEXTLINE)


    textStream.setFont(font,14F)

    var x: Float  // coordinates of detected text
    var y: Float

    for(textData in ocrResults) {
        x = textData.boundingBox.x.toFloat()
        y = textData.boundingBox.y.toFloat() - height

        if (y < 0) {
            y *= -1F
        }
        textStream.beginText()
        textStream.newLineAtOffset(x,y)
        textStream.showText(textData.text.replace("\n", "").replace("\r", ""))
        textStream.endText()
    }
    textStream.close()
    searchableDocument.save("Tess4jResults")
}