package com.lowagie.text.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.lowagie.text.Document;

public class PdfSmartCopyTest {
    
    public PdfSmartCopyTest() {
        super();
    }
    
    @Test
    public void test1() throws IOException {
        File orig = new File("src/test/resources/pdfsmartcopy_bec.pdf");
        check(orig, 1);
    }
    
    @Test
    public void test2() throws IOException {
        File orig = new File("src/test/resources/pdfsmartcopy_clave.pdf");
        check(orig, 2);
    }
        
    
    @Test
    public void test3() throws IOException {
        File orig = new File("src/test/resources/pdfsmartcopy_sircyl.pdf");
        check(orig, 3);
    }
    
//    @Test
//    public void test4() throws IOException {
//        File orig = new File("20220902/smart/gal_20220314.pdf");
//        check(orig, 4);
//    }
    
    
    private void check(File orig, int count) throws IOException {
        Assertions.assertTimeout(Duration.ofSeconds(5), () -> {
            File out = new File("target/test-classes/pdfsmartocy-" + count + ".pdf");
            out.getParentFile().mkdirs();
            Document document = new Document();
            FileOutputStream outputStream = new FileOutputStream(out);
            PdfCopy copy = new PdfSmartCopy(document, outputStream);
    //        PdfCopy copy = new PdfCopy(document, outputStream);
            document.open();
            
            PdfReader reader = new PdfReader(orig.getAbsolutePath());
            int n = reader.getNumberOfPages();
            for (int currentPage = 1; currentPage <= n; currentPage++) {
                PdfImportedPage page = copy.getImportedPage(reader, currentPage);
                copy.addPage(page);
            }
            copy.freeReader(reader);
            reader.close();
            document.close();
            copy.close();
        });
    }
    
    
    

}
