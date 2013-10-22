ParserVisualization
===================

A tool for creating Neo4j databases from parser results.

Currently, takes as input a parse output from the Stanford parser (http://nlp.stanford.edu/software/lex-parser.shtml) and displays the CFG and Dependency parses as Google Charts (https://developers.google.com/chart/image/docs/gallery/graphviz). Also creates a rudimentary Neo4j graph, a work in progress.

NOTE: Javadocs are available at http://glpayson.github.io/ParserVisualization<br>
NOTE: This particular type of Google Chart is deprecated by Google<br>
NOTE: Some example input/output is in ParserVisualization/SyntaxParseTools/<br>
NOTE: There is a UML diagram (which may or may not be up to date) for this project in ParserVisualization/SyntaxParseTools/

TODO:
- Currently working on converting from/to this program's graph data structure to/from Neo4j (http://www.neo4j.org), a graph database.
- - CFG's need indexing on the PARENT_OF property
- - DG's need to be implemented
- - Tighter binding between the DGNode/CFGNode classes and their corresponding Neo4j nodes. This will be necessary to fininishing the DG implementation
- Neo4j DB locations need to be made parameters, not hard coded
- Once Neo4j is in place, do some fancier visualizations, e.g. D3.js (http://d3js.org), Processing (http://www.processing.org), etc.
- Support more parsers. Both other PCFG and/or DG parsers which may have different output formats, and parsers for other grammar types, e.g. LFG, TAG, HPSG.
- Maven
