ParserVisualization
===================

A tool for visualizing parse results

Takes as input a parse output from the Stanford parser (http://nlp.stanford.edu/software/lex-parser.shtml) and displays the CFG and Dependency parses as Google Charts (https://developers.google.com/chart/image/docs/gallery/graphviz).

NOTE: Javadocs are available at http://glpayson.github.io/ParserVisualization<br>
NOTE: This particular type of Google Chart is deprecated by Google<br>
NOTE: Some example input/output is in ParserVisualization/SyntaxParseTools/<br>
NOTE: There is a UML diagram (which may or may not be up to date) for this project in ParserVisualization/SyntaxParseTools/

TODO:
- Currently working on converting from/to this program's graph data structure to/from Neo4j (http://www.neo4j.org), a graph database.
- Once Neo4j is in place, do some fancier visualizations, e.g. D3.js (http://d3js.org), Processing (http://www.processing.org), etc.
- Support more parsers. Both other PCFG and/or DG parsers which may have different output formats, and parsers for other grammar types, e.g. LFG, TAG, HPSG.
- Maven
