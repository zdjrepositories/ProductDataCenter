package pojo;

public class DocumentFile {
    private Long document_file_id;
    private Long doc_id;
    private Long size;
    private String extension;
    private String filename;

    public Long getDocument_file_id() {
        return document_file_id;
    }

    public void setDocument_file_id(Long document_file_id) {
        this.document_file_id = document_file_id;
    }

    public Long getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Long doc_id) {
        this.doc_id = doc_id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "DocumentFile{" +
                "document_file_id=" + document_file_id +
                ", doc_id=" + doc_id +
                ", size=" + size +
                ", extension='" + extension + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
