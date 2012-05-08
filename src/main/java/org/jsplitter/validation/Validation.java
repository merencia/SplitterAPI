package org.jsplitter.validation;

public class Validation {

    private String mensagem = null;
    private boolean ok = false;

    public Validation(boolean ok, String mensagem) {
        this.mensagem = mensagem;
        this.ok = ok;
    }

    public Validation() {
    }

    public Validation(boolean ok) {
        this.ok = ok;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
