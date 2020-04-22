package pojo;

public class Document {
    private  Long document_id;
    private String author;
    private String attributeLists;
    private String attributes;
    private String audience_id;
    private String audience_translation;
    private String createDate;
    private String desciption;
    private  Long range_id;
    private  Long docId;
    private String docOwner;
    private String documentDate;
    private String doctype_englishlabel;
    private  Long doctype_id;
    private String doctype_name;
    private String doctype_translation;
    private String expireDate;
    private String files_extension;
    private String files_filename;
    private  Long files_id ;
    private  Long files_size;
    private String keywords;
    private String lastModificationDate;
    private String locales_isoCountry;
    private String locales_isoLanguage;
    private  Long numberOfPage;
  private String reference;
    private String title;
    private String version;
    private int flipFlopGenerated;
private String description;

    @Override
    public String toString() {
        return "Document{" +
                "document_id=" + document_id +
                ", author='" + author + '\'' +
                ", attributeLists='" + attributeLists + '\'' +
                ", attributes='" + attributes + '\'' +
                ", audience_id='" + audience_id + '\'' +
                ", audience_translation='" + audience_translation + '\'' +
                ", createDate='" + createDate + '\'' +
                ", desciption='" + desciption + '\'' +
                ", range_id=" + range_id +
                ", docId=" + docId +
                ", docOwner='" + docOwner + '\'' +
                ", documentDate='" + documentDate + '\'' +
                ", doctype_englishlabel='" + doctype_englishlabel + '\'' +
                ", doctype_id=" + doctype_id +
                ", doctype_name='" + doctype_name + '\'' +
                ", doctype_translation='" + doctype_translation + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", files_extension='" + files_extension + '\'' +
                ", files_filename='" + files_filename + '\'' +
                ", files_id=" + files_id +
                ", files_size=" + files_size +
                ", keywords='" + keywords + '\'' +
                ", lastModificationDate='" + lastModificationDate + '\'' +
                ", locales_isoCountry='" + locales_isoCountry + '\'' +
                ", locales_isoLanguage='" + locales_isoLanguage + '\'' +
                ", numberOfPage=" + numberOfPage +
                ", reference='" + reference + '\'' +
                ", title='" + title + '\'' +
                ", version='" + version + '\'' +
                ", flipFlopGenerated=" + flipFlopGenerated +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDocument_id() {
        return document_id;
    }

    public void setDocument_id(Long document_id) {
        this.document_id = document_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAttributeLists() {
        return attributeLists;
    }

    public void setAttributeLists(String attributeLists) {
        this.attributeLists = attributeLists;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getAudience_id() {
        return audience_id;
    }

    public void setAudience_id(String audience_id) {
        this.audience_id = audience_id;
    }

    public String getAudience_translation() {
        return audience_translation;
    }

    public void setAudience_translation(String audience_translation) {
        this.audience_translation = audience_translation;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Long getRange_id() {
        return range_id;
    }

    public void setRange_id(Long range_id) {
        this.range_id = range_id;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getDocOwner() {
        return docOwner;
    }

    public void setDocOwner(String docOwner) {
        this.docOwner = docOwner;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public String getDoctype_englishlabel() {
        return doctype_englishlabel;
    }

    public void setDoctype_englishlabel(String doctype_englishlabel) {
        this.doctype_englishlabel = doctype_englishlabel;
    }

    public Long getDoctype_id() {
        return doctype_id;
    }

    public void setDoctype_id(Long doctype_id) {
        this.doctype_id = doctype_id;
    }

    public String getDoctype_name() {
        return doctype_name;
    }

    public void setDoctype_name(String doctype_name) {
        this.doctype_name = doctype_name;
    }

    public String getDoctype_translation() {
        return doctype_translation;
    }

    public void setDoctype_translation(String doctype_translation) {
        this.doctype_translation = doctype_translation;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getFiles_extension() {
        return files_extension;
    }

    public void setFiles_extension(String files_extension) {
        this.files_extension = files_extension;
    }

    public String getFiles_filename() {
        return files_filename;
    }

    public void setFiles_filename(String files_filename) {
        this.files_filename = files_filename;
    }

    public Long getFiles_id() {
        return files_id;
    }

    public void setFiles_id(Long files_id) {
        this.files_id = files_id;
    }

    public Long getFiles_size() {
        return files_size;
    }

    public void setFiles_size(Long files_size) {
        this.files_size = files_size;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(String lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getLocales_isoCountry() {
        return locales_isoCountry;
    }

    public void setLocales_isoCountry(String locales_isoCountry) {
        this.locales_isoCountry = locales_isoCountry;
    }

    public String getLocales_isoLanguage() {
        return locales_isoLanguage;
    }

    public void setLocales_isoLanguage(String locales_isoLanguage) {
        this.locales_isoLanguage = locales_isoLanguage;
    }

    public Long getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(Long numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getFlipFlopGenerated() {
        return flipFlopGenerated;
    }

    public void setFlipFlopGenerated(int flipFlopGenerated) {
        this.flipFlopGenerated = flipFlopGenerated;
    }
}
