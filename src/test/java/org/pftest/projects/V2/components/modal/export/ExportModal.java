package org.pftest.projects.V2.components.modal.export;

import org.pftest.enums.pagefly.ListingType;
import org.pftest.enums.pagefly.ModalType;
import org.pftest.projects.V2.components.modal.BaseModal;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.pftest.keywords.WebUI.*;

public class ExportModal extends BaseModal {
    public ExportModal(ListingType listingType) {
        super(ModalType.EXPORT);
        this.title = String.format(ModalType.EXPORT.getTitle(), listingType.toString().toLowerCase());
    }

    static public void verifyDownloadedExportedFile() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'export-pages-'yyyy-M-d-H-m");
        String formattedDateTime = now.format(formatter);
        System.out.println("Expected file name: " + formattedDateTime);
        assert verifyDownloadFileContainsName(formattedDateTime, 5);
    }

}
