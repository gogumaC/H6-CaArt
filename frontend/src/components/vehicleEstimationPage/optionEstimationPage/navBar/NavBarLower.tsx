import React from 'react';
import styled from 'styled-components';
import { useFetch } from '../../../../hooks/useFetch';
import { ErrorPopup } from '../../../common/ErrorPopup';
import { OptionNavBarProps } from './NavBar';

interface optionTagProps {
  tagId: number;
  tagName: string;
  tagImage: string;
  tagIcon: string;
  tagIconSelected: string;
  priority: number;
}

function OptionNavBarLower({
  isBasicOptionPage,
  optionCategory,
  setOptionCategory,
}: Pick<
  OptionNavBarProps,
  'isBasicOptionPage' | 'optionCategory' | 'setOptionCategory'
>) {
  const { data, status, error } = useFetch<optionTagProps[]>(
    `/tags/${isBasicOptionPage ? 'basic' : 'additional'}`,
  );
  if (status === 'loading') {
    return <div></div>;
  } else if (status === 'error') {
    console.error(error);
    return <ErrorPopup></ErrorPopup>;
  }
  if (data === null) return <div></div>;

  const selectedClassName = `body-medium-14 text-primary-blue`;
  const unSelectedClassName = `body-regular-14 text-grey-400`;

  const categoryLists = data.map(item => {
    return (
      <OptionNavCategoryBox
        key={item.tagId}
        className={item.tagName === optionCategory.name ? 'selected' : ''}
        onClick={() => {
          setOptionCategory({
            name: item.tagName,
            img: item.tagImage,
            id: item.tagId,
          });
        }}
      >
        <img
          src={
            item.tagName === optionCategory.name
              ? `${item.tagIconSelected}`
              : `${item.tagIcon}`
          }
        ></img>
        <span
          className={
            item.tagName === optionCategory.name
              ? selectedClassName
              : unSelectedClassName
          }
        >
          {item.tagName}
        </span>
      </OptionNavCategoryBox>
    );
  });

  return <OptionNavBarLowerBox>{categoryLists}</OptionNavBarLowerBox>;
}

const OptionNavBarLowerBox = styled.div`
  display: flex;
  gap: 8px;
  padding-top: 14px;
  padding-left: 128px;
`;

const OptionNavCategoryBox = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 7px 10px;
  border-radius: 4px;
  cursor: pointer;
  background: ${props =>
    props.className === 'selected' ? `var(--grey-1000)` : 'var(--grey-800)'};
  border: ${props =>
    props.className === 'selected'
      ? `1.5px solid var(--primary-blue)`
      : `1.5px solid transparent`};
  transition: all 0.5s;

  &:hover {
    border: 1.5px solid var(--primary-blue);
    background: var(--grey-1000);

    span {
      color: var(--primary-blue);
    }
  }
`;

export default OptionNavBarLower;
