"""
The longest cityname in the dataset is 
"""

citynames_disambiguate = {
	"London": 0,
	"Auschwitz": 0,
	"Rostov": 1,
	"New": 1,
	"Las": 1
}

citynames_multiword = {
	"New York", "Las Vegas", "Las Palmas", "Rostov", "Rostov na donu"
}

def printf(arg):
	print(arg)

def make_string_range(wordlist, a, b):
	substring = ""
	for x in range(a,b):
		substring += wordlist[x]
		if x >= b-1:
			break
		substring += " "
	return substring

def interesting(wordlist, a, b):
	substring = make_string_range(wordlist, a, b)
	if substring in citynames_multiword:
		printf("It must be {}".format(substring))
		return True
	return False

def disambiguate(wordlist, index):
	printf(wordlist)
	maxidx = len(wordlist)
	if maxidx > index + 6:
		maxidx = index + 6
	
	for x in range(maxidx, index, -1):
		if interesting(wordlist, index, x):
			return x - index;
	printf("wtf?")
	return 0

def tryme(sentence):
	printf("INPUT: {}".format(sentence))
	words = sentence.split()
	for i in range(0, len(words)):
		word = words[i].replace(".","")
		if word in citynames_disambiguate:
			if citynames_disambiguate[word] == 1:
				i += disambiguate(words, i)
				continue
			printf("Found single word {}".format(word))

if __name__ == "__main__":
	tryme("I live in London")
	tryme("I love in Las Palmas like Las Vegas")
	tryme("I was once in Auschwitz. It was nice")
	tryme("Las Zapdo Las Vegas")

