
package com.example.lisap.mynews.entities;

import java.util.List;

public class Response {
//contient liste doc
    private List<Doc> docs = null;
    private Meta meta;

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
