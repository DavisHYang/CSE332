package provided;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import p2.sorts.QuickSort;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSortTests {

	@Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
	public void test_sort_integerSorted_correctSort() {
		Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		Integer[] arr_sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		QuickSort.sort(arr, Integer::compareTo);
		for(int i = 0; i < arr.length; i++) {
			assertEquals(arr[i], arr_sorted[i]);
		}
	}

	@Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
	public void empty() {
		Integer[] arr = {};
		Integer[] arr_sorted = {};
		QuickSort.sort(arr, Integer::compareTo);
		for(int i = 0; i < arr.length; i++) {
			assertEquals(arr[i], arr_sorted[i]);
		}
	}
	@Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
	public void test_sort_integerRandom_correctSort() {
		Integer[] arr = {3, 1, 4, 5, 9, 2, 6, 7, 8};
		Integer[] arr_sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		QuickSort.sort(arr, Integer::compareTo);
		for(int i = 0; i < arr.length; i++) {
			assertEquals(arr[i], arr_sorted[i]);
		}
	}

	@Test()
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
	public void stress_test() {
		Integer[] arr = {-999, -998, -996, -994, -992, -991, -990, -989, -988, -987, -984, -983, -981, -979, -974, -973, -972, -971, -964, -962, -961, -960, -956, -951, -947, -945, -942, -940, -937, -936, -935, -934, -931, -930, -928, -927, -925, -923, -922, -921, -920, -919, -915, -912, -910, -909, -907, -905, -902, -899, -894, -891, -886, -885, -884, -883, -882, -880, -876, -875, -873, -871, -869, -868, -867, -863, -858, -857, -855, -854, -853, -849, -847, -845, -844, -843, -838, -837, -836, -835, -834, -832, -826, -825, -823, -822, -819, -813, -811, -810, -808, -806, -803, -802, -801, -800, -797, -796, -795, -791, -782, -781, -780, -778, -777, -774, -773, -772, -771, -770, -769, -762, -761, -759, -758, -757, -751, -750, -748, -744, -742, -741, -739, -738, -737, -736, -733, -731, -728, -726, -725, -724, -723, -722, -721, -719, -716, -715, -714, -712, -710, -707, -706, -703, -701, -700, -699, -696, -688, -679, -678, -673, -671, -666, -661, -660, -658, -656, -655, -652, -650, -649, -648, -645, -644, -643, -641, -638, -637, -635, -633, -632, -631, -629, -626, -625, -623, -622, -621, -620, -616, -615, -613, -612, -611, -609, -608, -607, -606, -605, -604, -603, -602, -592, -590, -588, -587, -586, -585, -583, -579, -577, -576, -575, -573, -572, -565, -564, -563, -561, -553, -549, -548, -545, -540, -537, -534, -533, -532, -531, -530, -529, -528, -527, -525, -522, -521, -519, -518, -513, -512, -511, -508, -507, -504, -503, -502, -501, -500, -499, -498, -497, -494, -492, -490, -489, -485, -484, -483, -482, -481, -480, -478, -477, -476, -475, -469, -467, -466, -462, -460, -459, -455, -454, -453, -451, -449, -448, -447, -445, -443, -441, -440, -437, -436, -433, -431, -426, -425, -424, -423, -422, -421, -419, -417, -415, -413, -411, -410, -409, -407, -405, -404, -403, -399, -398, -397, -396, -395, -394, -393, -391, -387, -385, -382, -378, -375, -374, -372, -371, -367, -365, -363, -361, -360, -356, -355, -351, -350, -348, -346, -345, -344, -342, -336, -335, -334, -333, -332, -331, -327, -325, -324, -323, -321, -317, -316, -314, -313, -312, -311, -310, -309, -307, -306, -303, -302, -301, -300, -298, -297, -295, -294, -292, -289, -287, -282, -281, -278, -276, -275, -274, -273, -271, -268, -266, -265, -264, -261, -260, -259, -258, -252, -250, -246, -243, -241, -240, -238, -235, -234, -231, -230, -228, -225, -224, -223, -221, -220, -219, -218, -217, -213, -212, -210, -209, -208, -207, -205, -204, -201, -199, -198, -195, -193, -192, -190, -184, -183, -181, -180, -177, -176, -174, -173, -172, -171, -170, -169, -168, -167, -166, -165, -163, -162, -161, -160, -151, -149, -148, -147, -144, -142, -132, -130, -129, -127, -126, -125, -123, -122, -121, -120, -117, -112, -109, -108, -106, -101, -100, -99, -98, -97, -94, -87, -86, -85, -84, -83, -82, -81, -79, -78, -77, -71, -69, -68, -65, -64, -63, -61, -58, -57, -55, -54, -51, -50, -49, -48, -46, -45, -44, -43, -39, -38, -37, -36, -35, -34, -33, -29, -27, -24, -23, -22, -21, -16, -13, -12, -11, -10, -9, -6, -5, -1, 2, 4, 6, 7, 8, 9, 10, 12, 13, 15, 16, 19, 21, 22, 26, 27, 28, 29, 30, 33, 34, 35, 37, 40, 41, 42, 43, 46, 47, 49, 52, 53, 54, 57, 64, 65, 70, 76, 77, 78, 81, 83, 84, 86, 89, 92, 95, 97, 98, 101, 102, 103, 104, 105, 106, 110, 119, 120, 122, 123, 124, 127, 129, 130, 132, 133, 135, 140, 141, 142, 143, 146, 147, 148, 149, 151, 153, 155, 158, 161, 162, 167, 170, 171, 172, 174, 176, 177, 178, 179, 181, 183, 184, 185, 187, 188, 190, 192, 199, 200, 203, 204, 205, 216, 218, 219, 220, 221, 224, 226, 229, 231, 233, 235, 238, 240, 241, 243, 244, 245, 246, 247, 248, 249, 251, 253, 254, 255, 256, 257, 259, 262, 263, 265, 268, 269, 271, 272, 274, 276, 278, 279, 280, 281, 283, 284, 286, 287, 289, 290, 291, 292, 294, 295, 296, 297, 300, 303, 307, 310, 311, 315, 317, 318, 320, 324, 325, 327, 328, 330, 333, 334, 335, 339, 344, 345, 348, 349, 356, 357, 362, 363, 364, 370, 371, 373, 378, 384, 385, 386, 387, 388, 391, 393, 394, 396, 397, 398, 399, 400, 401, 407, 411, 412, 415, 416, 418, 420, 422, 424, 425, 428, 430, 431, 434, 435, 437, 441, 442, 443, 444, 445, 446, 448, 452, 453, 456, 457, 459, 460, 461, 462, 465, 467, 471, 474, 475, 476, 477, 481, 482, 483, 486, 487, 489, 490, 495, 496, 497, 499, 501, 503, 504, 505, 509, 510, 511, 515, 517, 519, 521, 522, 525, 535, 542, 543, 544, 545, 546, 547, 548, 550, 551, 553, 555, 558, 559, 561, 564, 567, 568, 569, 570, 571, 573, 574, 576, 577, 578, 579, 580, 581, 583, 584, 585, 588, 591, 593, 595, 596, 597, 598, 599, 602, 603, 604, 605, 607, 611, 612, 613, 614, 616, 617, 619, 620, 621, 622, 627, 628, 634, 635, 637, 640, 643, 647, 648, 651, 653, 658, 660, 664, 666, 667, 668, 672, 677, 678, 680, 682, 684, 685, 686, 688, 692, 693, 694, 695, 697, 699, 700, 701, 702, 703, 706, 707, 708, 709, 712, 713, 714, 716, 718, 719, 720, 724, 726, 729, 733, 735, 736, 740, 743, 744, 745, 746, 747, 750, 752, 755, 761, 762, 766, 768, 772, 773, 774, 776, 782, 785, 792, 793, 794, 798, 799, 801, 802, 804, 805, 807, 809, 814, 817, 820, 821, 824, 827, 828, 831, 833, 834, 835, 836, 839, 840, 841, 843, 844, 845, 851, 853, 854, 855, 856, 857, 858, 861, 862, 865, 866, 868, 870, 872, 877, 880, 881, 882, 884, 886, 889, 892, 898, 900, 901, 902, 903, 906, 907, 912, 913, 914, 916, 917, 918, 922, 923, 924, 926, 928, 930, 931, 933, 934, 939, 940, 941, 942, 944, 945, 946, 947, 948, 949, 951, 952, 953, 954, 955, 960, 963, 964, 966, 967, 969, 971, 979, 981, 982, 986, 989, 991, 992, 996, 998, 999};
		Collections.shuffle(Arrays.asList(arr));
		QuickSort.printArr(arr);
		Integer[] arr_sorted = {-999, -998, -996, -994, -992, -991, -990, -989, -988, -987, -984, -983, -981, -979, -974, -973, -972, -971, -964, -962, -961, -960, -956, -951, -947, -945, -942, -940, -937, -936, -935, -934, -931, -930, -928, -927, -925, -923, -922, -921, -920, -919, -915, -912, -910, -909, -907, -905, -902, -899, -894, -891, -886, -885, -884, -883, -882, -880, -876, -875, -873, -871, -869, -868, -867, -863, -858, -857, -855, -854, -853, -849, -847, -845, -844, -843, -838, -837, -836, -835, -834, -832, -826, -825, -823, -822, -819, -813, -811, -810, -808, -806, -803, -802, -801, -800, -797, -796, -795, -791, -782, -781, -780, -778, -777, -774, -773, -772, -771, -770, -769, -762, -761, -759, -758, -757, -751, -750, -748, -744, -742, -741, -739, -738, -737, -736, -733, -731, -728, -726, -725, -724, -723, -722, -721, -719, -716, -715, -714, -712, -710, -707, -706, -703, -701, -700, -699, -696, -688, -679, -678, -673, -671, -666, -661, -660, -658, -656, -655, -652, -650, -649, -648, -645, -644, -643, -641, -638, -637, -635, -633, -632, -631, -629, -626, -625, -623, -622, -621, -620, -616, -615, -613, -612, -611, -609, -608, -607, -606, -605, -604, -603, -602, -592, -590, -588, -587, -586, -585, -583, -579, -577, -576, -575, -573, -572, -565, -564, -563, -561, -553, -549, -548, -545, -540, -537, -534, -533, -532, -531, -530, -529, -528, -527, -525, -522, -521, -519, -518, -513, -512, -511, -508, -507, -504, -503, -502, -501, -500, -499, -498, -497, -494, -492, -490, -489, -485, -484, -483, -482, -481, -480, -478, -477, -476, -475, -469, -467, -466, -462, -460, -459, -455, -454, -453, -451, -449, -448, -447, -445, -443, -441, -440, -437, -436, -433, -431, -426, -425, -424, -423, -422, -421, -419, -417, -415, -413, -411, -410, -409, -407, -405, -404, -403, -399, -398, -397, -396, -395, -394, -393, -391, -387, -385, -382, -378, -375, -374, -372, -371, -367, -365, -363, -361, -360, -356, -355, -351, -350, -348, -346, -345, -344, -342, -336, -335, -334, -333, -332, -331, -327, -325, -324, -323, -321, -317, -316, -314, -313, -312, -311, -310, -309, -307, -306, -303, -302, -301, -300, -298, -297, -295, -294, -292, -289, -287, -282, -281, -278, -276, -275, -274, -273, -271, -268, -266, -265, -264, -261, -260, -259, -258, -252, -250, -246, -243, -241, -240, -238, -235, -234, -231, -230, -228, -225, -224, -223, -221, -220, -219, -218, -217, -213, -212, -210, -209, -208, -207, -205, -204, -201, -199, -198, -195, -193, -192, -190, -184, -183, -181, -180, -177, -176, -174, -173, -172, -171, -170, -169, -168, -167, -166, -165, -163, -162, -161, -160, -151, -149, -148, -147, -144, -142, -132, -130, -129, -127, -126, -125, -123, -122, -121, -120, -117, -112, -109, -108, -106, -101, -100, -99, -98, -97, -94, -87, -86, -85, -84, -83, -82, -81, -79, -78, -77, -71, -69, -68, -65, -64, -63, -61, -58, -57, -55, -54, -51, -50, -49, -48, -46, -45, -44, -43, -39, -38, -37, -36, -35, -34, -33, -29, -27, -24, -23, -22, -21, -16, -13, -12, -11, -10, -9, -6, -5, -1, 2, 4, 6, 7, 8, 9, 10, 12, 13, 15, 16, 19, 21, 22, 26, 27, 28, 29, 30, 33, 34, 35, 37, 40, 41, 42, 43, 46, 47, 49, 52, 53, 54, 57, 64, 65, 70, 76, 77, 78, 81, 83, 84, 86, 89, 92, 95, 97, 98, 101, 102, 103, 104, 105, 106, 110, 119, 120, 122, 123, 124, 127, 129, 130, 132, 133, 135, 140, 141, 142, 143, 146, 147, 148, 149, 151, 153, 155, 158, 161, 162, 167, 170, 171, 172, 174, 176, 177, 178, 179, 181, 183, 184, 185, 187, 188, 190, 192, 199, 200, 203, 204, 205, 216, 218, 219, 220, 221, 224, 226, 229, 231, 233, 235, 238, 240, 241, 243, 244, 245, 246, 247, 248, 249, 251, 253, 254, 255, 256, 257, 259, 262, 263, 265, 268, 269, 271, 272, 274, 276, 278, 279, 280, 281, 283, 284, 286, 287, 289, 290, 291, 292, 294, 295, 296, 297, 300, 303, 307, 310, 311, 315, 317, 318, 320, 324, 325, 327, 328, 330, 333, 334, 335, 339, 344, 345, 348, 349, 356, 357, 362, 363, 364, 370, 371, 373, 378, 384, 385, 386, 387, 388, 391, 393, 394, 396, 397, 398, 399, 400, 401, 407, 411, 412, 415, 416, 418, 420, 422, 424, 425, 428, 430, 431, 434, 435, 437, 441, 442, 443, 444, 445, 446, 448, 452, 453, 456, 457, 459, 460, 461, 462, 465, 467, 471, 474, 475, 476, 477, 481, 482, 483, 486, 487, 489, 490, 495, 496, 497, 499, 501, 503, 504, 505, 509, 510, 511, 515, 517, 519, 521, 522, 525, 535, 542, 543, 544, 545, 546, 547, 548, 550, 551, 553, 555, 558, 559, 561, 564, 567, 568, 569, 570, 571, 573, 574, 576, 577, 578, 579, 580, 581, 583, 584, 585, 588, 591, 593, 595, 596, 597, 598, 599, 602, 603, 604, 605, 607, 611, 612, 613, 614, 616, 617, 619, 620, 621, 622, 627, 628, 634, 635, 637, 640, 643, 647, 648, 651, 653, 658, 660, 664, 666, 667, 668, 672, 677, 678, 680, 682, 684, 685, 686, 688, 692, 693, 694, 695, 697, 699, 700, 701, 702, 703, 706, 707, 708, 709, 712, 713, 714, 716, 718, 719, 720, 724, 726, 729, 733, 735, 736, 740, 743, 744, 745, 746, 747, 750, 752, 755, 761, 762, 766, 768, 772, 773, 774, 776, 782, 785, 792, 793, 794, 798, 799, 801, 802, 804, 805, 807, 809, 814, 817, 820, 821, 824, 827, 828, 831, 833, 834, 835, 836, 839, 840, 841, 843, 844, 845, 851, 853, 854, 855, 856, 857, 858, 861, 862, 865, 866, 868, 870, 872, 877, 880, 881, 882, 884, 886, 889, 892, 898, 900, 901, 902, 903, 906, 907, 912, 913, 914, 916, 917, 918, 922, 923, 924, 926, 928, 930, 931, 933, 934, 939, 940, 941, 942, 944, 945, 946, 947, 948, 949, 951, 952, 953, 954, 955, 960, 963, 964, 966, 967, 969, 971, 979, 981, 982, 986, 989, 991, 992, 996, 998, 999};
		QuickSort.sort(arr, Integer::compareTo);
		QuickSort.printArr(arr);
		for(int i = 0; i < arr.length; i++) {
			if(!arr[i].equals(arr_sorted[i])) {
				System.out.println(i + " " + arr[i]);
			}
			assertEquals(arr[i], arr_sorted[i]);
		}
	}

}