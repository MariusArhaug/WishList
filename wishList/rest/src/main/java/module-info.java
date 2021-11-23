module wishList.rest {
  requires com.fasterxml.jackson.databind;
  requires wishList.core;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires spring.boot;
  requires spring.web;
  requires spring.boot.starter.tomcat;
  requires java.sql;

  opens wishList.restapi to
      spring.beans,
      spring.context,
      spring.web;
}
