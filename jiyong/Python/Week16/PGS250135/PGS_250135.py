def solution(h1, m1, s1, h2, m2, s2):
    def get_times(h, m, s):
        seconds = h * 60 * 60 + m * 60 + s

        sd = (s * 6) % 360
        md = (m * 6 + s * 0.1) % 360
        hd = (h * 30 + m * 0.5 + s * 0.5 / 60) % 360

        ret = (h * 60 + m) * 2 - h

        if sd >= md:
            ret += 1
        if sd >= hd:
            ret += 1

        if h >= 12:
            ret -= 2

        return ret

    ret = get_times(h2, m2, s2) - get_times(h1, m1, s1)

    if h1 in [0, 12] and m1 == 0 and s1 == 0:
        ret += 1

    return ret