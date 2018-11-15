package com.sgdn.sgdn.SrcDataBase;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sgdn.sgdn.Dominio.Alerta;
import com.sgdn.sgdn.Dominio.Componente;
import com.sgdn.sgdn.Dominio.Inspecao;
import com.sgdn.sgdn.Dominio.Maquina;
import com.sgdn.sgdn.Dominio.Usuario;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DataBase extends SQLiteOpenHelper {

    Activity activity;


    public DataBase(Activity activity) {
        super(activity, "banco.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabelas
        String tabelaUsuario = "create table if not exists usuario (idUsuario Integer not null primary key," +
                "nome varchar(100) not null," +
                "email varchar(80) not null," +
                "senha varchar(12) not null)";
        db.execSQL(tabelaUsuario);

        String tabelaMaquina = "create table if not exists maquina (idMaquina Integer primary key," +
                "nome varchar(100) not null," +
                "fabricante varchar(80) not null)";
        db.execSQL(tabelaMaquina);

        String tabelaComponente = "create table if not exists componente (idComponente Integer primary key," +
                "nome varchar(100) not null," +
                "qtdHorasTrabalho Integer not null," +
                "alturaMinimaDesgaste Real not null," +
                "larguraMinimaDesgaste Real not null," +
                "fkMaquina Integer not null)";
        db.execSQL(tabelaComponente);

        String tabelaInspecao = "create table if not exists inspecao (idInspecao Integer primary key," +
                "qtdHorasTrabalhadas Integer not null," +
                "qtdHorasDia Integer not null," +
                "qtdDiasSemana Integer not null," +
                "alturaDesgaste Real not null," +
                "larguraDesgaste Real not null," +
                "cliente varchar(100) not null," +
                "cidade varchar(40) not null," +
                "celular varchar(80) not null," +
                "fkComponente Integer not null)";
        db.execSQL(tabelaInspecao);

        String tabelaAlerta = "create table if not exists alerta (idAlerta Integer primary key," +
                "dataAlerta varchar(100) not null," +
                "estadoAlerta varchar(30)," +
                "fkInspecao Integer not null)";
        db.execSQL(tabelaAlerta);

        Usuario usuario = new Usuario();
        usuario.setNome("Jutai Campos");
        usuario.setEmail("jutai.s@hotmail.com");
        usuario.setSenha("310391");
        String sql = "Insert into usuario(nome,email,senha) values('" + usuario.getNome() + "','" + usuario.getEmail() + "','" + usuario.getSenha() + "') ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /************************** Chamadas Usuario ****************************************/
    public ArrayList<Usuario> getUsuarios() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from usuario";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(c.getInt(0));
                usuario.setNome(c.getString(1));
                usuario.setEmail(c.getString(2));
                usuario.setSenha(c.getString(3));
                usuarios.add(usuario);
            } while (c.moveToNext());
        }
        c.close();
        return usuarios;
    }

    /************************** Chamadas Máquina ****************************************/
    public void salvarMaquina(Maquina maquina) {
        ContentValues valores = new ContentValues();
        valores.put("nome", maquina.getNome());
        valores.put("fabricante", maquina.getFabricante());
        getWritableDatabase().insert("maquina", null, valores);
    }

    public ArrayList<Maquina> getMaquinas() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from maquina";
        ArrayList<Maquina> maquinas = new ArrayList<>();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Maquina maquina = new Maquina();
                maquina.setIdMaquina(c.getInt(0));
                maquina.setNome(c.getString(1));
                maquina.setFabricante(c.getString(2));
                maquinas.add(maquina);
            } while (c.moveToNext());
        }
        c.close();
        return maquinas;
    }

    public Maquina getMaquinaById(Maquina maquina) {
        SQLiteDatabase db = getReadableDatabase();
        Maquina maquinaRetorno = null;
        String query = "select * from maquina WHERE idMaquina = '" + maquina.getIdMaquina() + "'";
        //ArrayList<Maquina> maquinas = new ArrayList<>();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                maquinaRetorno = new Maquina();
                maquina.setIdMaquina(c.getInt(0));
                maquina.setNome(c.getString(1));
                maquina.setFabricante(c.getString(2));
            } while (c.moveToNext());
        }
        c.close();
        return maquina;
    }

    public void updateMaquina(Maquina maquina) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "update maquina set nome='" + maquina.getNome() + "',fabricante='" + maquina.getFabricante() + "' " +
                "where idMaquina='" + maquina.getIdMaquina() + "'";
        db.execSQL(query);
    }

    /************************** Chamadas Componente ****************************************/

    public long salvarComponente(Componente componente) {
        ContentValues valores = new ContentValues();
        valores.put("nome", componente.getNome());
        valores.put("qtdHorasTrabalho", componente.getQtdHorasTrabalho());
        valores.put("alturaMinimaDesgaste", componente.getAlturaMinimaDesgaste());
        valores.put("larguraMinimaDesgaste", componente.getLarguraMinimaDesgaste());
        valores.put("fkMaquina", componente.getMaquina().getIdMaquina());
        long i = getWritableDatabase().insert("componente", null, valores);

        return i;
    }

    public ArrayList<Componente> getComponentes() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from componente";
        ArrayList<Componente> componentes = new ArrayList<>();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Componente componente = new Componente();
                componente.setIdComponente(c.getInt(0));
                componente.setNome(c.getString(1));
                componente.setQtdHorasTrabalho(c.getInt(2));
                componente.setAlturaMinimaDesgaste(c.getDouble(3));
                componente.setLarguraMinimaDesgaste(c.getDouble(4));
                int idMaquina = c.getInt(5);
                Maquina maquinaBusca = new Maquina();
                maquinaBusca.setIdMaquina(idMaquina);
                Maquina maquinaComponente = getMaquinaById(maquinaBusca);
                componente.setMaquina(maquinaComponente);
                componentes.add(componente);
            } while (c.moveToNext());
        }
        c.close();
        return componentes;
    }

    public Componente getComponenteById(Componente componente) {
        SQLiteDatabase db = getReadableDatabase();
        Componente componenteRetorno = null;
        String query = "select * from componente WHERE idComponente = '" + componente.getIdComponente() + "'";
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                componenteRetorno = new Componente();
                componenteRetorno.setIdComponente(c.getInt(0));
                componenteRetorno.setNome(c.getString(1));
                componenteRetorno.setQtdHorasTrabalho(c.getInt(2));
                componenteRetorno.setAlturaMinimaDesgaste(c.getDouble(3));
                componenteRetorno.setLarguraMinimaDesgaste(c.getDouble(4));
                int idMaquina = c.getInt(5);
                Maquina maquinaBusca = new Maquina();
                maquinaBusca.setIdMaquina(idMaquina);
                Maquina maquinaComponente = getMaquinaById(maquinaBusca);
                componenteRetorno.setMaquina(maquinaComponente);
            } while (c.moveToNext());
        }
        c.close();
        return componenteRetorno;
    }

    public void updateComponente(Componente componente) {

        SQLiteDatabase db = getReadableDatabase();
        String query = "update componente set nome='" + componente.getNome() + "',qtdHorasTrabalho='"
                + componente.getQtdHorasTrabalho() + "'," +
                "alturaMinimaDesgaste='" + componente.getAlturaMinimaDesgaste() + "', " +
                "larguraMinimaDesgaste = '" + componente.getLarguraMinimaDesgaste() + "'," +
                "fkmaquina = '" + componente.getMaquina().getIdMaquina() + "' " +
                "where idComponente='" + componente.getIdComponente() + "'";
        db.execSQL(query);
    }

    public ArrayList<Componente> getComponenteByMaquina(Maquina maquina) {

        SQLiteDatabase db = getReadableDatabase();
        Componente componenteRetorno;
        ArrayList<Componente> listaComponentes = new ArrayList<>();
        String query = "select * from componente WHERE fkMaquina = '" + maquina.getIdMaquina() + "'";
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                componenteRetorno = new Componente();
                componenteRetorno.setIdComponente(c.getInt(0));
                componenteRetorno.setNome(c.getString(1));
                componenteRetorno.setQtdHorasTrabalho(c.getInt(2));
                componenteRetorno.setAlturaMinimaDesgaste(c.getDouble(3));
                componenteRetorno.setLarguraMinimaDesgaste(c.getDouble(4));
                int idMaquina = c.getInt(5);
                Maquina maquinaBusca = new Maquina();
                maquinaBusca.setIdMaquina(idMaquina);
                Maquina maquinaComponente = getMaquinaById(maquinaBusca);
                componenteRetorno.setMaquina(maquinaComponente);
                listaComponentes.add(componenteRetorno);
            } while (c.moveToNext());
        }
        c.close();
        return listaComponentes;
    }

    /************************** Chamadas Inspeção ******************************************/
    public long salvarInspecao(Inspecao inspecao) {
        ContentValues valores = new ContentValues();
        valores.put("qtdHorasTrabalhadas", inspecao.getQtdHorasTrabalho());
        valores.put("qtdHorasDia", inspecao.getQtdHorasDia());
        valores.put("qtdDiasSemana", inspecao.getQtdDiasSemana());
        valores.put("alturaDesgaste", inspecao.getAlturaDesgaste());
        valores.put("larguraDesgaste", inspecao.getLarguraDesgaste());
        valores.put("cliente", inspecao.getCliente());
        valores.put("cidade", inspecao.getCidade());
        valores.put("celular", inspecao.getCelularCliente());
        valores.put("fkComponente", inspecao.getComponente().getIdComponente());
        long i = getWritableDatabase().insert("inspecao", null, valores);
        return i;
    }

    public ArrayList<Inspecao> getinspecoes() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from inspecao";
        ArrayList<Inspecao> inspecoes = new ArrayList<>();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Inspecao inspecao = new Inspecao();
                inspecao.setIdInspecao(c.getInt(0));
                inspecao.setQtdHorasTrabalho(c.getInt(1));
                inspecao.setQtdHorasDia(c.getInt(2));
                inspecao.setQtdDiasSemana(c.getInt(3));
                inspecao.setAlturaDesgaste(c.getDouble(4));
                inspecao.setLarguraDesgaste(c.getDouble(5));
                inspecao.setCliente(c.getString(6));
                inspecao.setCidade(c.getString(7));
                inspecao.setCelularCliente(c.getString(8));
                int idComponente = c.getInt(9);
                Componente componenteBusca = new Componente();
                componenteBusca.setIdComponente(idComponente);
                Componente inspecaoComponente = getComponenteById(componenteBusca);
                inspecao.setComponente(inspecaoComponente);
                inspecoes.add(inspecao);
            } while (c.moveToNext());
        }
        c.close();
        return inspecoes;
    }

    public Inspecao getInspecaoById(Inspecao inspecao) {
        SQLiteDatabase db = getReadableDatabase();
        Inspecao inspecaoRetorno = new Inspecao();
        String query = "select * from inspecao WHERE idInspecao = '" + inspecao.getIdInspecao() + "'";
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                inspecaoRetorno.setIdInspecao(c.getInt(0));
                inspecaoRetorno.setQtdHorasTrabalho(c.getInt(1));
                inspecao.setQtdHorasDia(c.getInt(2));
                inspecao.setQtdDiasSemana(c.getInt(3));
                inspecaoRetorno.setAlturaDesgaste(c.getDouble(4));
                inspecaoRetorno.setLarguraDesgaste(c.getDouble(5));
                inspecaoRetorno.setCliente(c.getString(6));
                inspecaoRetorno.setCidade(c.getString(7));
                inspecaoRetorno.setCelularCliente(c.getString(8));

                int idComponente = c.getInt(9);
                Componente componenteBusca = new Componente();
                componenteBusca.setIdComponente(idComponente);
                Componente componenteRecuperado = getComponenteById(componenteBusca);
                inspecaoRetorno.setComponente(componenteRecuperado);
            } while (c.moveToNext());
        }
        c.close();
        return inspecaoRetorno;
    }

    /************************** Chamadas Alerta*********************************************/

    public long salvarAlerta(Alerta alerta) {
        ContentValues valores = new ContentValues();
        valores.put("dataAlerta", alerta.getDataAlerta().toString());
        valores.put("estadoAlerta", "pendente");
        valores.put("fkInspecao", alerta.getInspecao().getIdInspecao());
        long i = getWritableDatabase().insert("alerta", null, valores);
        return i;
    }

    public Alerta getAlertaById(Alerta alerta) {
        SQLiteDatabase db = getReadableDatabase();
        Alerta alertaRetorno = null;
        String query = "select * from alerta WHERE idAlerta = '" + alerta.getIdAlerta() + "'";
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                alertaRetorno = new Alerta();
                alertaRetorno.setIdAlerta(c.getInt(0));
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();
                alertaRetorno.setDataAlerta(date.toString());
                alertaRetorno.setEstado(c.getString(2));
                int idInspecao = c.getInt(3);
                Inspecao inspecaoBusca = new Inspecao();
                inspecaoBusca.setIdInspecao(idInspecao);
                Inspecao inspecaoRecuperada = getInspecaoById(inspecaoBusca);
                alertaRetorno.setInspecao(inspecaoRecuperada);
            } while (c.moveToNext());
        }
        c.close();
        return alertaRetorno;
    }

    public ArrayList<Alerta> getAlertas() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from alerta";
        ArrayList<Alerta> alertas = new ArrayList<>();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Alerta alerta = new Alerta();
                alerta.setIdAlerta(c.getInt(0));
                alerta.setDataAlerta(c.getString(1));
                alerta.setEstado(c.getString(2));
                int idInspecao = c.getInt(3);
                Inspecao inspecaoBusca = new Inspecao();
                inspecaoBusca.setIdInspecao(idInspecao);
                Inspecao inspecaoRecuperada = getInspecaoById(inspecaoBusca);
                alerta.setInspecao(inspecaoRecuperada);
                alertas.add(alerta);
            } while (c.moveToNext());
        }
        c.close();
        return alertas;
    }

    public void updateAlerta(Alerta alerta) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "update alerta set estadoAlerta='notificado' where idAlerta='" + alerta.getIdAlerta() + "'";
        db.execSQL(query);
    }

    public ArrayList<Alerta> getAlertasNotificado() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from alerta where estadoAlerta='notificado'";
        ArrayList<Alerta> alertas = new ArrayList<>();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Alerta alerta = new Alerta();
                alerta.setIdAlerta(c.getInt(0));
                alerta.setDataAlerta(c.getString(1));
                alerta.setEstado(c.getString(2));
                int idInspecao = c.getInt(3);
                Inspecao inspecaoBusca = new Inspecao();
                inspecaoBusca.setIdInspecao(idInspecao);
                inspecaoBusca = getInspecaoById(inspecaoBusca);
                alerta.setInspecao(inspecaoBusca);
                alertas.add(alerta);
            } while (c.moveToNext());
        }
        c.close();
        return alertas;
    }

}
