package flatten;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFFormFlattener {

    public static final String USAGE = "java -jar PDFFormFlattener.jar {{input_file}} {{output_file}}";

    public static void flattenPDF(String inputFile, String outputFile) {
        PdfDocument pdfDoc = null;
        try {
            pdfDoc = new PdfDocument(new PdfReader(new FileInputStream(inputFile)),
                    new PdfWriter(new FileOutputStream(outputFile)));
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, false);
            form.flattenFields();
            pdfDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
	    if (args.length != 2) {
            System.out.println(String.format("Input & Output pdf file paths are required.\nUsage:\n\t%s", USAGE));
            return;
        }
	    String inputFile = args[0];
	    String outputFile = args[1];

        PDFFormFlattener.flattenPDF(inputFile, outputFile);
    }
}
