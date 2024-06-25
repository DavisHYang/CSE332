package provided;

import cse332.interfaces.worklists.PriorityWorkList;
import cse332.interfaces.worklists.WorkList;
import datastructures.worklists.MinFourHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class MinFourHeapTests {
    private static final int SEED = 42;

    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_hasWork_empty_noWork() {
        WorkList<Integer> STUDENT_INT = new MinFourHeap<>(Integer::compareTo);
        assertFalse(STUDENT_INT.hasWork());
    }

    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_hasWork_oneElement_hasWork() {
        WorkList<Integer> STUDENT_INT = new MinFourHeap<>(Integer::compareTo);
        STUDENT_INT.add(1);
        assertTrue(STUDENT_INT.hasWork());
    }

    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_addNextHasWork_manyElements_noWork() {
        WorkList<Double> STUDENT_DOUBLE = new MinFourHeap<>(Double::compareTo);
        for (int i = 0; i < 1000; i++) {
            STUDENT_DOUBLE.add(Math.random());
        }
        for (int i = 0; i < 1000; i++) {
            STUDENT_DOUBLE.next();
        }
        assertFalse(STUDENT_DOUBLE.hasWork());
    }
    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_peek_fewElements_throwsException() {
        WorkList<Integer> STUDENT_INT = new MinFourHeap<>(Integer::compareTo);
        assertThrows(NoSuchElementException.class, () -> {
            STUDENT_INT.peek();
        });

        addAndRemove(STUDENT_INT, 42, 10);
        assertThrows(NoSuchElementException.class, () -> {
            STUDENT_INT.peek();
        });
    }

    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_next_fewElements_throwsException() {
        WorkList<Integer> STUDENT_INT = new MinFourHeap<>(Integer::compareTo);
        assertThrows(NoSuchElementException.class, () -> {
            STUDENT_INT.next();
        });

        addAndRemove(STUDENT_INT, 42, 10);
        assertThrows(NoSuchElementException.class, () -> {
            STUDENT_INT.next();
        });
    }
    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_clear_fewElements_empty() {
        WorkList<String> STUDENT_STR = new MinFourHeap<>(String::compareTo);
        addAll(STUDENT_STR, new String[]{"Beware", "the", "Jabberwock", "my", "son!"});

        assertTrue(STUDENT_STR.hasWork());
        assertEquals(5, STUDENT_STR.size());

        STUDENT_STR.clear();
        assertFalse(STUDENT_STR.hasWork());
        assertEquals(0, STUDENT_STR.size());
        assertThrows(NoSuchElementException.class, () -> {
            STUDENT_STR.peek();
        });
        assertThrows(NoSuchElementException.class, () -> {
            STUDENT_STR.next();
        });
    }

    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_addNext_fewElements_correctStructure() {
        PriorityWorkList<String> heap = new MinFourHeap<>(String::compareTo);
        String[] tests = { "a", "b", "c", "d", "e" };
        for (int i = 0; i < 5; i++) {
            String str = tests[i] + "a";
            heap.add(str);
        }

        for (int i = 0; i < 5; i++) {
            String str_heap = heap.next();
            String str = (char) ('a' + i) + "a";
            assertEquals(str, str_heap);
        }
    }

    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_addPeekNext_differentOrderings_correctStructure() {
        PriorityWorkList<String> ordered = new MinFourHeap<>(String::compareTo);
        PriorityWorkList<String> reversed = new MinFourHeap<>(String::compareTo);
        PriorityWorkList<String> random = new MinFourHeap<>(String::compareTo);

        addAll(ordered, new String[]{"a", "b", "c", "d", "e"});
        addAll(reversed, new String[]{"e", "d", "c", "b", "a"});
        addAll(random, new String[]{"d", "b", "c", "e", "a"});

        assertTrue(isSame("a", ordered.peek(), reversed.peek(), random.peek()));
        assertTrue(isSame("a", ordered.next(), reversed.next(), random.next()));
        assertTrue(isSame("b", ordered.next(), reversed.next(), random.next()));

        addAll(ordered, new String[] {"a", "a", "b", "c", "z"});
        addAll(reversed, new String[] {"z", "c", "b", "a", "a"});
        addAll(random, new String[] {"c", "z", "a", "b", "a"});

        String[] expected = new String[] {"a", "a", "b", "c", "c", "d", "e", "z"};
        for (String e : expected) {
            assertTrue(isSame(e, ordered.peek(), reversed.peek(), random.peek()));
            assertTrue(isSame(e, ordered.next(), reversed.next(), random.next()));
        }
    }

    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_addNext_manyElements_correctStructure() {
        PriorityWorkList<String> heap = new MinFourHeap<>(String::compareTo);
        int n = 10000;

        // Add them
        for (int i = 0; i < n; i++) {
            String str = String.format("%05d", i * 37 % n);
            heap.add(str);
        }
        // Delete them all
        for (int i = 0; i < n; i++) {
            String s = heap.next();
            assertEquals(i , Integer.parseInt(s));
        }
    }

    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_customComparator_manyElements_correctStructure() {
        Random RAND = new Random(SEED);
        PriorityWorkList<Coordinate> student = new MinFourHeap<>(Coordinate::compareTo);
        Queue<Coordinate> reference = new PriorityQueue<>();

        for (int i = 0; i < 10000; i++) {
            Coordinate coord = new Coordinate(RAND.nextInt(10000) - 5000, RAND.nextInt(10000) - 5000);
            student.add(coord);
            reference.add(coord);
        }
        assertEquals(reference.size(), student.size());

        while (!reference.isEmpty()) {
            assertEquals(reference.peek() , student.peek());
            assertEquals(reference.remove() , student.next());
        }
    }

    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_addNext_severalElements_correctStructure() {
        PriorityWorkList<Integer> heap = new MinFourHeap<>(Integer::compareTo);
        addAll(heap, new Integer[] {10, 10, 15, 1, 17, 16, 100, 101, 102, 103, 105, 106, 107, 108});

        Object[] heapData = getField(heap, "data");
        String heapStr = Arrays.toString(heapData);
        String heapExp = "[1, 10, 15, 10, 17, 16, 100, 101, 102, 103, 105, 106, 107, 108";

        heap.next();
        heap.next();
        heap.next();

        Object[] heapData2 = getField(heap, "data");
        String heapStr2 = Arrays.toString(heapData2);
        String heapExp2 = "[15, 16, 103, 107, 17, 108, 100, 101, 102, 106, 105,";

        assertTrue(heapStr.contains(heapExp));
        assertTrue(heapStr2.contains(heapExp2));
    }
    @Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void fuzzytest() {
        PriorityWorkList<Integer> heap = new MinFourHeap<>(Integer::compareTo);
        addAll(heap, new Integer[] {-1000, -998, -996, -995, -994, -993, -990, -985, -982, -981, -977, -976, -973, -972, -970, -968, -966, -965, -962, -961, -960, -955, -954, -951, -949, -948, -946, -944, -943, -942, -941, -940, -939, -937, -936, -933, -932, -931, -926, -925, -924, -922, -921, -920, -918, -917, -916, -914, -913, -912, -911, -908, -905, -904, -899, -898, -897, -894, -892, -891, -890, -889, -887, -886, -884, -882, -879, -876, -872, -871, -870, -867, -866, -862, -860, -858, -856, -855, -853, -851, -850, -849, -848, -846, -836, -832, -828, -826, -824, -822, -821, -820, -819, -818, -816, -814, -810, -809, -806, -804, -803, -800, -798, -797, -796, -792, -790, -786, -784, -783, -782, -781, -780, -779, -777, -772, -765, -762, -761, -759, -758, -756, -755, -754, -752, -751, -748, -744, -743, -742, -741, -740, -737, -736, -734, -733, -730, -728, -727, -725, -724, -722, -721, -720, -714, -712, -711, -709, -708, -706, -705, -702, -701, -693, -692, -691, -688, -687, -686, -684, -683, -682, -681, -680, -678, -677, -674, -670, -669, -660, -658, -656, -655, -652, -651, -650, -647, -643, -642, -640, -638, -636, -632, -630, -629, -628, -627, -626, -624, -619, -616, -615, -613, -612, -606, -604, -603, -598, -588, -587, -585, -583, -582, -581, -578, -576, -575, -573, -570, -569, -568, -566, -565, -564, -561, -557, -555, -554, -551, -537, -530, -526, -524, -523, -521, -517, -516, -514, -513, -511, -510, -509, -504, -503, -501, -500, -496, -495, -494, -493, -491, -490, -487, -486, -484, -482, -481, -477, -476, -475, -469, -468, -467, -466, -465, -464, -463, -462, -461, -460, -459, -456, -455, -451, -448, -447, -446, -445, -440, -438, -435, -430, -429, -424, -423, -420, -419, -418, -417, -416, -415, -414, -406, -404, -403, -402, -400, -399, -397, -396, -395, -394, -390, -389, -386, -382, -379, -373, -370, -369, -368, -367, -366, -365, -360, -359, -358, -357, -354, -353, -351, -349, -348, -347, -346, -345, -344, -343, -341, -340, -338, -335, -334, -333, -332, -331, -328, -327, -325, -323, -321, -320, -319, -318, -317, -314, -312, -311, -309, -304, -303, -301, -300, -299, -296, -295, -294, -292, -291, -288, -285, -282, -280, -278, -274, -269, -267, -265, -264, -258, -257, -256, -255, -254, -253, -252, -251, -243, -240, -238, -235, -234, -233, -232, -231, -229, -228, -227, -226, -222, -218, -216, -214, -213, -211, -209, -207, -205, -204, -202, -201, -200, -199, -196, -195, -194, -193, -183, -181, -177, -174, -172, -171, -168, -162, -161, -159, -158, -156, -154, -153, -152, -150, -149, -140, -137, -135, -134, -133, -132, -129, -126, -124, -123, -120, -117, -116, -112, -109, -108, -107, -106, -105, -103, -102, -101, -100, -99, -92, -91, -90, -89, -87, -85, -81, -80, -79, -76, -70, -68, -66, -64, -63, -62, -59, -55, -51, -50, -49, -46, -44, -43, -41, -40, -35, -33, -32, -31, -30, -27, -26, -25, -24, -23, -22, -21, -20, -17, -16, -15, -11, -10, -8, -5, 3, 9, 10, 12, 13, 14, 17, 18, 20, 22, 26, 28, 29, 30, 31, 32, 33, 37, 38, 40, 41, 42, 44, 48, 49, 52, 53, 55, 58, 59, 60, 64, 69, 70, 71, 75, 76, 77, 79, 80, 81, 82, 84, 89, 90, 95, 96, 98, 99, 101, 102, 106, 107, 109, 110, 116, 118, 120, 121, 122, 128, 131, 132, 134, 135, 137, 138, 139, 140, 143, 144, 145, 146, 149, 151, 156, 158, 159, 161, 163, 164, 166, 168, 169, 170, 175, 177, 178, 179, 180, 182, 183, 185, 186, 190, 192, 194, 195, 199, 200, 201, 203, 205, 207, 208, 209, 210, 214, 215, 217, 224, 225, 228, 229, 230, 231, 237, 238, 242, 249, 251, 252, 254, 256, 257, 258, 259, 260, 261, 262, 264, 267, 270, 274, 275, 279, 280, 282, 283, 285, 286, 288, 289, 290, 291, 292, 293, 297, 300, 301, 302, 305, 307, 308, 310, 314, 317, 318, 320, 321, 324, 329, 331, 332, 334, 335, 336, 338, 340, 342, 346, 347, 348, 349, 350, 351, 354, 355, 356, 357, 361, 365, 366, 370, 374, 376, 377, 378, 381, 382, 383, 384, 385, 386, 388, 393, 395, 396, 398, 399, 401, 408, 409, 412, 413, 417, 418, 419, 420, 421, 422, 424, 425, 426, 429, 431, 432, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 448, 453, 455, 459, 460, 461, 462, 463, 470, 471, 474, 475, 478, 479, 480, 481, 483, 484, 485, 486, 488, 489, 491, 492, 495, 496, 504, 507, 508, 510, 511, 513, 514, 515, 516, 519, 520, 522, 524, 526, 528, 530, 531, 532, 533, 539, 540, 541, 542, 546, 548, 551, 553, 554, 556, 559, 561, 567, 569, 570, 571, 574, 576, 578, 580, 581, 584, 586, 587, 588, 589, 593, 596, 598, 599, 600, 601, 604, 605, 606, 608, 609, 610, 611, 612, 613, 614, 616, 619, 620, 621, 623, 624, 632, 637, 639, 642, 644, 646, 648, 651, 654, 655, 657, 658, 661, 663, 664, 665, 668, 669, 678, 683, 686, 687, 688, 689, 690, 692, 695, 696, 702, 705, 707, 708, 709, 710, 711, 716, 719, 724, 725, 726, 727, 728, 729, 730, 731, 739, 740, 742, 744, 746, 747, 750, 751, 752, 753, 754, 755, 756, 759, 760, 763, 764, 767, 768, 770, 772, 773, 775, 776, 777, 780, 782, 783, 786, 787, 788, 789, 790, 792, 795, 796, 799, 801, 802, 804, 807, 811, 812, 813, 815, 817, 819, 820, 821, 822, 827, 831, 833, 837, 840, 841, 842, 843, 845, 846, 851, 853, 854, 855, 857, 859, 861, 862, 865, 866, 867, 868, 869, 870, 878, 879, 880, 881, 882, 884, 885, 886, 888, 889, 890, 891, 892, 893, 895, 896, 897, 901, 902, 904, 906, 908, 911, 912, 913, 915, 916, 917, 923, 925, 926, 928, 929, 931, 932, 936, 937, 938, 941, 942, 943, 944, 945, 946, 947, 948, 952, 953, 954, 955, 958, 961, 962, 965, 966, 967, 969, 970, 971, 972, 973, 975, 978, 981, 982, 984, 985, 987, 988, 989, 992, 995, 996, 997, 1000});
        PriorityQueue<Integer> x = new PriorityQueue<>();
        x.addAll(Arrays.asList(new Integer[] {-1000, -998, -996, -995, -994, -993, -990, -985, -982, -981, -977, -976, -973, -972, -970, -968, -966, -965, -962, -961, -960, -955, -954, -951, -949, -948, -946, -944, -943, -942, -941, -940, -939, -937, -936, -933, -932, -931, -926, -925, -924, -922, -921, -920, -918, -917, -916, -914, -913, -912, -911, -908, -905, -904, -899, -898, -897, -894, -892, -891, -890, -889, -887, -886, -884, -882, -879, -876, -872, -871, -870, -867, -866, -862, -860, -858, -856, -855, -853, -851, -850, -849, -848, -846, -836, -832, -828, -826, -824, -822, -821, -820, -819, -818, -816, -814, -810, -809, -806, -804, -803, -800, -798, -797, -796, -792, -790, -786, -784, -783, -782, -781, -780, -779, -777, -772, -765, -762, -761, -759, -758, -756, -755, -754, -752, -751, -748, -744, -743, -742, -741, -740, -737, -736, -734, -733, -730, -728, -727, -725, -724, -722, -721, -720, -714, -712, -711, -709, -708, -706, -705, -702, -701, -693, -692, -691, -688, -687, -686, -684, -683, -682, -681, -680, -678, -677, -674, -670, -669, -660, -658, -656, -655, -652, -651, -650, -647, -643, -642, -640, -638, -636, -632, -630, -629, -628, -627, -626, -624, -619, -616, -615, -613, -612, -606, -604, -603, -598, -588, -587, -585, -583, -582, -581, -578, -576, -575, -573, -570, -569, -568, -566, -565, -564, -561, -557, -555, -554, -551, -537, -530, -526, -524, -523, -521, -517, -516, -514, -513, -511, -510, -509, -504, -503, -501, -500, -496, -495, -494, -493, -491, -490, -487, -486, -484, -482, -481, -477, -476, -475, -469, -468, -467, -466, -465, -464, -463, -462, -461, -460, -459, -456, -455, -451, -448, -447, -446, -445, -440, -438, -435, -430, -429, -424, -423, -420, -419, -418, -417, -416, -415, -414, -406, -404, -403, -402, -400, -399, -397, -396, -395, -394, -390, -389, -386, -382, -379, -373, -370, -369, -368, -367, -366, -365, -360, -359, -358, -357, -354, -353, -351, -349, -348, -347, -346, -345, -344, -343, -341, -340, -338, -335, -334, -333, -332, -331, -328, -327, -325, -323, -321, -320, -319, -318, -317, -314, -312, -311, -309, -304, -303, -301, -300, -299, -296, -295, -294, -292, -291, -288, -285, -282, -280, -278, -274, -269, -267, -265, -264, -258, -257, -256, -255, -254, -253, -252, -251, -243, -240, -238, -235, -234, -233, -232, -231, -229, -228, -227, -226, -222, -218, -216, -214, -213, -211, -209, -207, -205, -204, -202, -201, -200, -199, -196, -195, -194, -193, -183, -181, -177, -174, -172, -171, -168, -162, -161, -159, -158, -156, -154, -153, -152, -150, -149, -140, -137, -135, -134, -133, -132, -129, -126, -124, -123, -120, -117, -116, -112, -109, -108, -107, -106, -105, -103, -102, -101, -100, -99, -92, -91, -90, -89, -87, -85, -81, -80, -79, -76, -70, -68, -66, -64, -63, -62, -59, -55, -51, -50, -49, -46, -44, -43, -41, -40, -35, -33, -32, -31, -30, -27, -26, -25, -24, -23, -22, -21, -20, -17, -16, -15, -11, -10, -8, -5, 3, 9, 10, 12, 13, 14, 17, 18, 20, 22, 26, 28, 29, 30, 31, 32, 33, 37, 38, 40, 41, 42, 44, 48, 49, 52, 53, 55, 58, 59, 60, 64, 69, 70, 71, 75, 76, 77, 79, 80, 81, 82, 84, 89, 90, 95, 96, 98, 99, 101, 102, 106, 107, 109, 110, 116, 118, 120, 121, 122, 128, 131, 132, 134, 135, 137, 138, 139, 140, 143, 144, 145, 146, 149, 151, 156, 158, 159, 161, 163, 164, 166, 168, 169, 170, 175, 177, 178, 179, 180, 182, 183, 185, 186, 190, 192, 194, 195, 199, 200, 201, 203, 205, 207, 208, 209, 210, 214, 215, 217, 224, 225, 228, 229, 230, 231, 237, 238, 242, 249, 251, 252, 254, 256, 257, 258, 259, 260, 261, 262, 264, 267, 270, 274, 275, 279, 280, 282, 283, 285, 286, 288, 289, 290, 291, 292, 293, 297, 300, 301, 302, 305, 307, 308, 310, 314, 317, 318, 320, 321, 324, 329, 331, 332, 334, 335, 336, 338, 340, 342, 346, 347, 348, 349, 350, 351, 354, 355, 356, 357, 361, 365, 366, 370, 374, 376, 377, 378, 381, 382, 383, 384, 385, 386, 388, 393, 395, 396, 398, 399, 401, 408, 409, 412, 413, 417, 418, 419, 420, 421, 422, 424, 425, 426, 429, 431, 432, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 448, 453, 455, 459, 460, 461, 462, 463, 470, 471, 474, 475, 478, 479, 480, 481, 483, 484, 485, 486, 488, 489, 491, 492, 495, 496, 504, 507, 508, 510, 511, 513, 514, 515, 516, 519, 520, 522, 524, 526, 528, 530, 531, 532, 533, 539, 540, 541, 542, 546, 548, 551, 553, 554, 556, 559, 561, 567, 569, 570, 571, 574, 576, 578, 580, 581, 584, 586, 587, 588, 589, 593, 596, 598, 599, 600, 601, 604, 605, 606, 608, 609, 610, 611, 612, 613, 614, 616, 619, 620, 621, 623, 624, 632, 637, 639, 642, 644, 646, 648, 651, 654, 655, 657, 658, 661, 663, 664, 665, 668, 669, 678, 683, 686, 687, 688, 689, 690, 692, 695, 696, 702, 705, 707, 708, 709, 710, 711, 716, 719, 724, 725, 726, 727, 728, 729, 730, 731, 739, 740, 742, 744, 746, 747, 750, 751, 752, 753, 754, 755, 756, 759, 760, 763, 764, 767, 768, 770, 772, 773, 775, 776, 777, 780, 782, 783, 786, 787, 788, 789, 790, 792, 795, 796, 799, 801, 802, 804, 807, 811, 812, 813, 815, 817, 819, 820, 821, 822, 827, 831, 833, 837, 840, 841, 842, 843, 845, 846, 851, 853, 854, 855, 857, 859, 861, 862, 865, 866, 867, 868, 869, 870, 878, 879, 880, 881, 882, 884, 885, 886, 888, 889, 890, 891, 892, 893, 895, 896, 897, 901, 902, 904, 906, 908, 911, 912, 913, 915, 916, 917, 923, 925, 926, 928, 929, 931, 932, 936, 937, 938, 941, 942, 943, 944, 945, 946, 947, 948, 952, 953, 954, 955, 958, 961, 962, 965, 966, 967, 969, 970, 971, 972, 973, 975, 978, 981, 982, 984, 985, 987, 988, 989, 992, 995, 996, 997, 1000}));
        while(heap.hasWork()) {
            assertEquals(x.remove(), heap.next());
        }
    }

    public static class Coordinate implements Comparable<Coordinate> {
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // What exactly this comparable method is doing is somewhat arbitrary.
        public int compareTo(Coordinate other) {
            if (this.x != other.x) {
                return this.x - other.x;
            } else {
                return this.y - other.y;
            }
        }
    }


    private boolean isSame(String... args) {
        String first = args[0];
        for (String arg : args) {
            if (!first.equals(arg)) {
                return false;
            }
        }
        return true;
    }

    protected static <E> void addAll(WorkList<E> worklist, E[] values) {
        for (E value : values) {
            worklist.add(value);
        }
    }

    protected static <E> void addAndRemove(WorkList<E> worklist, E value, int amount) {
        for (int i = 0; i < amount; i++) {
            worklist.add(value);
        }
        for (int i = 0; i < amount; i++) {
            worklist.next();
        }
    }

    protected <T> T getField(Object o, String fieldName) {
        try {
            Field field = o.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object f = field.get(o);
            return (T) f;
        } catch (Exception var6) {
            try {
                Field field = o.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object f = field.get(o);
                return (T) f;
            } catch (Exception var5) {
                return null;
            }
        }
    }
}

