## Generates primes between 2 and sup
def getPrimes(sup):
    numbers = [1 for x in xrange(sup+1)]
    primes = []
    for x in xrange(2, int(sup**0.5)+2):
        if numbers[x]==1:
            mul = 2
            while(mul*x<=sup):
                numbers[mul*x] = 0
                mul += 1
    for x in xrange(2,sup+1):
        if numbers[x]==1:
            primes.append(x)
    return primes

print getPrimes(100)