package br.com.zup.ecommerce.provider.mail;

public class Mail {

    private final String title;
    private final String body;
    private final String target;

    public Mail(String title, String body, String target) {
        this.title = title;
        this.body = body;
        this.target = target;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", target='" + target + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getTarget() {
        return target;
    }
}
