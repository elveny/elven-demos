package com.gen.jpa.main;

import com.mysema.query.codegen.GenericExporter;
import com.mysema.query.codegen.Keywords;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.beans.Transient;
import java.io.File;

public class Querydsl {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GenericExporter exporter = new GenericExporter();
        exporter.setKeywords(Keywords.JPA);
        exporter.setEntityAnnotation(Entity.class);
        exporter.setEmbeddableAnnotation(Embeddable.class);
        exporter.setEmbeddedAnnotation(Embedded.class);
        exporter.setSupertypeAnnotation(MappedSuperclass.class);
        exporter.setSkipAnnotation(Transient.class);
        exporter.setTargetFolder(new File("target/generated-sources/java"));
        exporter.export(Querydsl.class.getPackage());
    }

}