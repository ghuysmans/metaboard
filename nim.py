#!/usr/bin/env python2
"""
Nim strategy explanation tool.
by Guillaume Huysmans and Florent Delgrange, 2016.
"""

def ai_ally(i, m):
	"""
	AI helping an ally.
	m is the maximum number of tokens we can take.
	i is the total number of tokens.
	"""
	if i%(m+1)==1 and i>1:
		#don't make the ally lose and avoid -1
		return i-2
	else:
		#that's safe
		return i-1

def ai_opp(i, m):
	"""
	AI playing against an opponent.
	m is the maximum number of tokens we can take.
	i is the total number of tokens.
	"""
	#i-1 enforces removing at least one token
	return min(i-1, i-i%(m+1))

def edge(a, b, style=None):
	"""
	Emit an edge from a to b with an optional style string.
	"""
	print a, "->", b, "["+style+"];" if style else ";"

def graph(start, m):
	print "digraph nim {"
	print "rankdir=LR;"
	for i in range(1, start+1):
		edge(i, ai_ally(i, m), "color=blue, style=dashed")
		edge(i, ai_opp(i, m), "color=red")
	print "}"


import sys
try:
	start = int(sys.argv[1])
	m = int(sys.argv[2])
except:
	print >>sys.stderr, "usage: %s start max" % sys.argv[0]
	sys.exit(1)
graph(start, m)
