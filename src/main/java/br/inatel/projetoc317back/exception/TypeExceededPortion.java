package br.inatel.projetoc317back.exception;

public class TypeExceededPortion extends RuntimeException{

    public TypeExceededPortion (double exceeded) {super ("Types must sum 100%, this exceeded by " + exceeded + " %.");}
}
