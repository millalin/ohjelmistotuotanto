package statistics;

import statistics.matcher.*;

import javax.management.Query;
import java.util.LinkedList;

public class QueryBuilder {

    public Matcher query;
    private Matcher[] matchers;

    public QueryBuilder() {

        this.query = new All();
    }

    public Matcher build() {
        return new And(query);
    }


    public Or oneOf(Matcher[] matchers) {
        return new Or(matchers);
    }

    public QueryBuilder playsIn(String team) {
    this.query = new And(this.query, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
this.query = new And(this.query,new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
this.query = new And(this.query,new HasFewerThan(value, category));
        return this;
    }

}
