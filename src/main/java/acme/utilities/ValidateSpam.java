
package acme.utilities;

import java.util.ArrayList;
import java.util.List;

import acme.framework.entities.Spam;

public class ValidateSpam {

	public boolean validateSpam(final String text, final Spam spam) {
		final Integer tamText = text.split(" ").length;
		Integer textCounter = 0;
		Integer textCounterCompous = 0;
		final List<String> spamWords = spam.getWords();

		final List<String> spamRefactor = new ArrayList<>();
		for (final String w : spamWords) {
			if (w.startsWith(" ")) {
				spamRefactor.add(w.substring(1, w.length()));
			} else {
				spamRefactor.add(w);
			}
		}

		for (final String w : spamRefactor) {
			final Integer numSpaces = w.split(" ").length - 1;
			textCounterCompous = text.contains(w) && w.contains(" ") ? textCounterCompous + numSpaces : textCounterCompous;
			textCounter = text.contains(w) ? textCounter + 1 : textCounter;
		}

		return Double.valueOf(textCounter) / Double.valueOf((tamText - textCounterCompous)) * 100 
			>= spam.getThreshold();
	}
}
