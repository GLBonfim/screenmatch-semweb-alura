package br.com.alura.screenmatchh.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
