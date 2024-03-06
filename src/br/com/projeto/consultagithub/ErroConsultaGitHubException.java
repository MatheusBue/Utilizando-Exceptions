package br.com.projeto.consultagithub;

public class ErroConsultaGitHubException extends RuntimeException{
    public ErroConsultaGitHubException(String mensagem) {
        super(mensagem);
    }
}
