/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela;

import at.lingu.jrela.projection.AllFromSourceProjection;
import at.lingu.jrela.projection.AllProjection;
import at.lingu.jrela.projection.SourceColumnProjection;

/**
 *
 * @author flo
 */
public interface AstVisitor {

	void visit(SelectStatement selectStatement);

	void visit(AllFromSourceProjection projection);

	void visit(AllProjection projection);

	void visit(SourceColumnProjection projection);
}
