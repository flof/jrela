/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.projection;

/**
 *
 * @author flo
 */
public interface ProjectionVisitor {

	void visit(AllFromSourceProjection projection);

	void visit(AllProjection projection);

	void visit(SourceColumnProjection projection);

}
