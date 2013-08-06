package com.jsf.common;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QEmp extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<Emp> implements PersistableExpression<Emp>
{
    public static final QEmp jdoCandidate = candidate("this");

    public static QEmp candidate(String name)
    {
        return new QEmp(null, name, 5);
    }

    public static QEmp candidate()
    {
        return jdoCandidate;
    }

    public static QEmp parameter(String name)
    {
        return new QEmp(Emp.class, name, ExpressionType.PARAMETER);
    }

    public static QEmp variable(String name)
    {
        return new QEmp(Emp.class, name, ExpressionType.VARIABLE);
    }

    public final NumericExpression<Integer> id;
    public final StringExpression name;
    public final StringExpression desi;
    public final StringExpression salary;

    public QEmp(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.id = new NumericExpressionImpl<Integer>(this, "id");
        this.name = new StringExpressionImpl(this, "name");
        this.desi = new StringExpressionImpl(this, "desi");
        this.salary = new StringExpressionImpl(this, "salary");
    }

    public QEmp(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.id = new NumericExpressionImpl<Integer>(this, "id");
        this.name = new StringExpressionImpl(this, "name");
        this.desi = new StringExpressionImpl(this, "desi");
        this.salary = new StringExpressionImpl(this, "salary");
    }
}
