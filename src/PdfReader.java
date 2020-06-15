import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.lowagie.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;

	public class PdfReader {

	    public static void main(String[] args) throws IOException {

	        //try (PDDocument document = PDDocument.load(new File("C:\\Users\\AG30447\\Desktop\\NS_Designated_AIP_employers.pdf"))) {

	    	File file = new File("C:\\Users\\AG30447\\Desktop\\NS_Designated_AIP_employers.pdf");
	        PDDocument document = PDDocument.load(file);

	        //Instantiate PDFTextStripper class
	        PDFTextStripper pdfStripper = new PDFTextStripper();

	        //Retrieving text from PDF document
	        String text = pdfStripper.getText(document);
	        System.out.println(text);

	        //Closing the document
	        document.close();

	     }
	}


