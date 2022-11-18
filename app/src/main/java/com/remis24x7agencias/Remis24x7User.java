package com.remis24x7agencias;

public class Remis24x7User {
        private boolean firstStart;
        private boolean recordarLogin;
        private int user_id;
        private String user_name;
        private String user_password;
        private String user_apellido;
        private String user_nombre;
        private String user_celular;
        private int user_tipo;
        private int user_per_id;
        private int user_empresa_id;
        private String user_empresa;
        private String user_email;

        public Remis24x7User(boolean firstStart, boolean recordarLogin, int user_id, String user_name, String user_password, String user_apellido, String user_nombre, String user_celular, int user_tipo, int user_per_id, int user_empresa_id, String user_empresa, String user_email) {
            this.firstStart = firstStart;
            this.recordarLogin = recordarLogin;
            this.user_id = user_id;
            this.user_name = user_name;
            this.user_password = user_password;
            this.user_apellido = user_apellido;
            this.user_nombre = user_nombre;
            this.user_celular = user_celular;
            this.user_tipo = user_tipo;
            this.user_per_id = user_per_id;
            this.user_empresa_id = user_empresa_id;
            this.user_empresa = user_empresa;
            this.user_email = user_email;
        }

        public boolean getFirstStart() {
            return firstStart;
        }

        public void setFirstStart(boolean firstStart) {
            this.firstStart = firstStart;
        }

        public boolean getRecordarLogin() {
            return recordarLogin;
        }

        public void setRecordarLogin(boolean recordarLogin) {
            this.recordarLogin = recordarLogin;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public String getUser_apellido() {
            return user_apellido;
        }

        public void setUser_apellido(String user_apellido) {
            this.user_apellido = user_apellido;
        }

        public String getUser_nombre() {
            return user_nombre;
        }

        public void setUser_nombre(String user_nombre) {
            this.user_nombre = user_nombre;
        }

        public String getUser_celular() {
            return user_celular;
        }

        public void setUser_celular(String user_celular) {
            this.user_celular = user_celular;
        }

        public int getUser_tipo() {
            return user_tipo;
        }

        public void setUser_tipo(int user_tipo) {
            this.user_tipo = user_tipo;
        }

        public int getUser_per_id() {
            return user_per_id;
        }

        public void setUser_per_id(int user_per_id) {
            this.user_per_id = user_per_id;
        }


        public int getUser_empresa_id() {
            return user_empresa_id;
        }

        public void setUser_empresa_id(int user_empresa_id) {
            this.user_empresa_id = user_empresa_id;
        }

        public String getUser_empresa() {
            return user_empresa;
        }

        public void setUser_empresa(String user_empresa) {
            this.user_empresa = user_empresa;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }
    }


