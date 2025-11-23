package service;

public interface ConverteDadosInterface {
    public <T> T obterDados(String json, Class<T> classe);
}
