package findmybmw.backend.service;

import findmybmw.backend.model.Media;
import findmybmw.backend.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    public List<Media> getMediaByPostId(Integer postId) {
        return mediaRepository.findByPostId(postId);
    }

    public Media saveMedia(Media media) {
        return mediaRepository.save(media);
    }
}
