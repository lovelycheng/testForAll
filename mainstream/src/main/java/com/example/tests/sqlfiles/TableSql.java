package com.example.tests.sqlfiles;

import java.util.List;

/**
 * @author chengtong
 * @date 2021/9/11 16:54
 */
public class TableSql implements Comparable<TableSql>{

    String date;

    String database;

    String table;

    List<DeclareContext> declareContexts;
    List<CreateContext> createContexts;
    List<TableSpaceContext> tableSpaceContexts;
    List<CommentContext> commentContexts;
    List<AlterContext> alterContexts;

    List<String> warnings;


    @Override
    public int compareTo(TableSql o) {
        return this.date.compareTo(o.date);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<DeclareContext> getDeclareContexts() {
        return declareContexts;
    }

    public void setDeclareContexts(List<DeclareContext> declareContexts) {
        this.declareContexts = declareContexts;
    }

    public List<CreateContext> getCreateContexts() {
        return createContexts;
    }

    public void setCreateContexts(List<CreateContext> createContexts) {
        this.createContexts = createContexts;
    }

    public List<TableSpaceContext> getTableSpaceContexts() {
        return tableSpaceContexts;
    }

    public void setTableSpaceContexts(List<TableSpaceContext> tableSpaceContexts) {
        this.tableSpaceContexts = tableSpaceContexts;
    }

    public List<CommentContext> getCommentContexts() {
        return commentContexts;
    }

    public void setCommentContexts(List<CommentContext> commentContexts) {
        this.commentContexts = commentContexts;
    }

    public List<AlterContext> getAlterContexts() {
        return alterContexts;
    }

    public void setAlterContexts(List<AlterContext> alterContexts) {
        this.alterContexts = alterContexts;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

}
