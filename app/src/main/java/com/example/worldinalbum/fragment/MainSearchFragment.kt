package com.example.worldinalbum.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.worldinalbum.R
import com.example.worldinalbum.adapter.TodayGoodWordAdapter
import com.example.worldinalbum.adapter.ViewPagerAdapter
import com.example.worldinalbum.databinding.FragmentMainSearchBinding


class MainSearchFragment() : Fragment() {

    private lateinit var binding : FragmentMainSearchBinding

    private val goodWordList = mutableListOf<String>()

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun getViewpagerImages() : ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.dog_image,
            R.drawable.cat_image,
            R.drawable.pig_image
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_search, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = ViewPagerAdapter(getViewpagerImages())

        val viewpager = binding.mainSearchFragViewpager
        viewpager.apply {

        adapter = viewPagerAdapter
        orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.dotsIndicator.attachTo(this)
        }


        goodWordList.add("세상은 고통으로 가득하지만\n" +
                "그것을 극복하는 사람들로도 가득하다\n" +
                "\n" +
                "- 헬렌 켈러")
        goodWordList.add("고난의 시기에 동요하지 않는 것,\n" +
                "이것은 진정 칭찬받을 만한 뛰어난 인물의 증거다\n" +
                "\n" +
                "- 베토벤")
        goodWordList.add("이미 끝나버린 일을 후회하기보다는\n" +
                "하고 싶었던 일들을 하지 못한 것을 후회하라\n" +
                "\n" +
                "- 탈무드")
        goodWordList.add("나만이 내 인생을 바꿀 수 있다\n" +
                "아무도 날 대신해 줄 수 없다\n" +
                "\n" +
                "- 캐롤 버넷")
        goodWordList.add("자신감 있는 표정을 지으면 자신감이 생긴다\n" +
                "\n" +
                "- 찰스다윈")
        goodWordList.add("행복은 습관이다\n" +
                "그것을 몸에 지녀라\n" +
                "\n" +
                "- 허버드")
        goodWordList.add("어리석은 자는 멀리서 행복을 찾고\n" +
                "현명한 자는 자신의 발치에서 행복을 키워간다\n" +
                "\n" +
                "- 제임스 오펜하임")
        goodWordList.add("성공의 비결은 단 한 가지,\n" +
                "잘할 수 있는 일에 광적으로 집중하는 것이다\n" +
                "\n" +
                "- 톰 모나건")
        goodWordList.add("미래를 신뢰하지 마라, 죽은 과거는 묻어버려라, \n" +
                "그리고 살아있는 현재에 행동하라. \n" +
                "\n" +
                "- 롱펠로")
        goodWordList.add("시간은 말로써 나타낼 수 없을 만큼 멋진 만물의 소재이다.\n" +
                "\n" +
                "- 아널드 버넷")
        goodWordList.add("고통이 남기고 간 뒤를 보라.\n" +
                "고난이 지나면 반드시 기쁨이 스며든다.\n" +
                "\n" +
                "- 괴테")
        goodWordList.add("화려한 일을 추구하지 말라. \n" +
                "중요한 것은 스스로의 재능이며, 자신의 행동에 쏟아붓는 사랑의 정도이다.\n" +
                "\n" +
                "- 마더 테레사")
        goodWordList.add("꿈을 계속 간직하고 있으면 반드시 실현할 때가 온다.\n" +
                "\n" +
                "- 괴테")
        goodWordList.add("인생에 뜻을 세우는 데 있어 늦은 때라곤 없다.\n" +
                "\n" +
                "- 볼드윈")
        goodWordList.add("용기 있는 자로 살아라.\n" +
                "운이 따라주지 않는다면 용기 있는 가슴으로 불행에 맞서라.\n" +
                "\n" +
                "- 키케로")
        goodWordList.add("독수리는 마지막 성공을 거둘 때까지 온 생명을 다한다.\n" +
                "\n" +
                "- 여안교\n")
        goodWordList.add("중요한 건 당신이 어떻게 시작했는가가 아니라,\n" +
                "어떻게 끝내는 가이다.\n" +
                "\n" +
                "- 매튜스")
        goodWordList.add("사람은 자기가 한 약속을 지킬만한 좋은 기억력을 가져야 한다.\n" +
                "\n" +
                "- 니체")
        goodWordList.add("금전은 비료와 같은 것으로,\n" +
                "뿌리지 않으면 쓸모가 없다.\n" +
                "\n" +
                "- 베이컨")
        goodWordList.add("지도자란 희망을 파는 상인이다.\n" +
                "\n" +
                "- 나폴레옹")
        goodWordList.add("전력을 다해서 시간에 대항하라.\n" +
                "\n" +
                "- 톨스토이")
        goodWordList.add("나는 장래의 일을 절대로 생각하지 않는다.\n" +
                "그것은 틀림없이 곧 오게 될 테니까.\n" +
                "\n" +
                "- 아인슈타인")
        goodWordList.add("오늘 할 수 있는 일에만 전력을 쏟으라.\n" +
                "그러면 내일에는 한걸음 더 진보한다.\n" +
                "\n" +
                "- 아이작 뉴턴")

        val randomGoodWord = mutableListOf(goodWordList.random())


        val goodWordListview = binding.goodWordListview
        goodWordListview.adapter = TodayGoodWordAdapter(randomGoodWord)

    }

}